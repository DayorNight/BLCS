package com.blcs.comlibs.widget;

import java.util.List;

/**
 * @ClassName: IAnimationLayout
 * @Author: KaiSenGao
 * @CreateDate: 2020/12/24 10:57
 * @Description:
 */
public interface IAnimationLayout {

    /**
     * 添加 资源文件
     *
     * @param resId resId
     */
    void addLikeImage(int resId);

    /**
     * 添加 资源文件组
     *
     * @param resIds resIds
     */
    void addLikeImages(Integer... resIds);

    /**
     * 添加 资源文件列表
     *
     * @param resIds resIds
     */
    void addLikeImages(List<Integer> resIds);

    /**
     * 添加 发送
     */
    void addFavor();
}