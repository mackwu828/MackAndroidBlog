package com.mackwu.component.ui.view.select;

/**
 * @author MackWu
 * @since 2022/7/8 11:21
 */
public interface SelectKey {

    /**
     * 区分数据唯一性的键值，作为选中和非选中Map中的Key。
     * See {@link SelectMode} 实体类需要实现该接口，使泛型可以获取实体类的属性。
     */
    String getKey();
}
