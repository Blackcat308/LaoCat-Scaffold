package org.laocat.core.utils;

import lombok.experimental.UtilityClass;
import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LaoCat
 * @description: bean复制工具类 效率比 beanUtils.copyProperties高
 * @date 2022/5/27 16:30
 * @see BeanCopier
 * @see org.springframework.beans.BeanUtils
 */
@UtilityClass
public class VoUtils {
    /**
     * 用内存来优化效率
     */
    private final Map<String, BeanCopier> beanCopierMap = new HashMap<>();

    public BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
        String key = source.getTypeName() + ":" + target.getTypeName();
        BeanCopier beanCopier = beanCopierMap.get(key);
        if (beanCopier == null) {
            beanCopier = BeanCopier.create(source, target, false);
            beanCopierMap.put(key, beanCopier);
        }
        return beanCopier;
    }

    /**
     * @return T
     * @author LaoCat
     * @date 2022/5/31
     * @description: copy bean 类型相同
     */
    public <T> T copy(Object source, Class<T> target) {
        T o = null;

        try {
            o = target.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        getBeanCopier(source.getClass(), target).copy(source, o, null);
        return o;
    }

    /**
     * @return java.util.List<T>
     * @author LaoCat
     * @date 2022/5/31
     * @description: copy beans 同理
     */
    public <S, T> List<T> copy(List<S> listSource, Class<T> target) {
        return listSource.stream()
                .map(source -> copy(source, target))
                .collect(Collectors.toList());
    }
}
