package org.laocat.controller;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson2.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.req.ReimbursementAddReq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @description: 示例
 * @author: LaoCat
 * @date: 2023/7/8
 */
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("example")
@Api(tags = "工作流示例【费用报销】")
public class ExampleController {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final RepositoryService repositoryService;
    private final ProcessEngine processEngine;

    /**
     * @description: 添加报销
     * @author: LaoCat
     * @date: 2023/7/8
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.Object>
     */
    @PostMapping("reimbursement")
    @ApiOperation(value = "添加报销", notes = "添加报销")
    @ResponseBody
    public ResponseEntity<Object> addReimbursement(@Valid @RequestBody ReimbursementAddReq reimbursementAddReq) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("taskUser", reimbursementAddReq.getUserId());
        paramMap.put("money", reimbursementAddReq.getMoney());
        ProcessInstance example = runtimeService.startProcessInstanceByKey("example", paramMap);
        log.info("example = {}",example.toString());
        return ResponseEntity.success(example.getId());
    }


    /**
     * @description: 查询审批管理列表
     * @author: LaoCat
     * @date: 2023/7/8
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.Object>
     */
    @GetMapping("management/list/{userId}")
    @ApiOperation(value = "查询审批管理列表", notes = "查询审批管理列表")
    @ResponseBody
    public ResponseEntity<Object> managementList(@PathVariable String userId) {
        List<Task> taskList =
                taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        return ResponseEntity.success(taskList.toString());
    }

    /**
     * @description: 批准
     * @author: LaoCat
     * @date: 2023/7/8
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.Object>
     */
    @PostMapping("apply/{taskId}")
    @ApiOperation(value = "批准", notes = "批准")
    @ResponseBody
    public ResponseEntity<Object> apply(@PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        if (Objects.isNull(task)) {
            throw new RuntimeException("该流程不存在");
        }

        taskService.complete(taskId, MapUtil.of("outcome", "通过"));
        return ResponseEntity.success();
    }


    /**
     * @description: 拒绝
     * @author: LaoCat
     * @date: 2023/7/8
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.Object>
     */
    @PostMapping("reject/{taskId}")
    @ApiOperation(value = "拒绝", notes = "拒绝")
    @ResponseBody
    public ResponseEntity<Object> reject(@PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        if (Objects.isNull(task)) {
            throw new RuntimeException("该流程不存在");
        }

        taskService.complete(taskId, MapUtil.of("outcome", "驳回"));
        return ResponseEntity.success();
    }

    /**
     * @description: 生成流程图
     * @author: LaoCat
     * @date: 2023/7/8
     * @returnType: void
     */
    @SneakyThrows
    @GetMapping("processDiagram/{processId}")
    @ApiOperation(value = "生成流程图", notes = "生成流程图")
    public void genProcessDiagram(HttpServletRequest request, HttpServletResponse response, @PathVariable String processId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();

        if (Objects.nonNull(processInstance)) {
            String processInstanceId = processInstance.getId();
            String processDefinitionId = processInstance.getProcessDefinitionId();

            Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

            String instanceId = task.getProcessInstanceId();
            List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(instanceId).list();

            List<String> activityIds = new LinkedList<>();
            executions.forEach(execution -> {
                activityIds.addAll(runtimeService.getActiveActivityIds(execution.getId()));
            });

            BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
            ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
            ProcessDiagramGenerator processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();

            InputStream inputStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", activityIds, Collections.EMPTY_LIST,
                    processEngineConfiguration.getActivityFontName(), processEngineConfiguration.getLabelFontName()
                    , processEngineConfiguration.getAnnotationFontName(), null, 1.0, false);

            OutputStream outputStream = null;
            byte[] bytes = new byte[1024];
            int length = 0;
            try {
                outputStream = response.getOutputStream();
                while ((length = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, length);
                }

            } finally {
                if (Objects.nonNull(inputStream)) {
                    inputStream.close();
                }
                if (Objects.nonNull(outputStream)) {
                    outputStream.close();
                }
            }
        }

    }
}
