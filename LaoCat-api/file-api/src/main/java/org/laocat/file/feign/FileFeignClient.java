package org.laocat.file.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: 文件服务
 * @author: LaoCat
 * @date: 2023/4/4
 */
@FeignClient(value = "LaoCat-file", contextId = "LaoCat-file")
public interface FileFeignClient {


}
