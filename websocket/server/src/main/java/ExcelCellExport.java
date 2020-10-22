/**
 * despaction
 *
 * @Author: jerry
 * @date: 2019/10/21 11:43
 * @description:
 **/
public @interface ExcelCellExport {
    /**
     * 单元格的名称
     *
     * @return
     */
    String name() default "";

    /**
     * 导出时，需要合并几列
     * @return
     */
    int colspan() default 0;

    /**
     * 导出时，需要合并几行
     * @return
     */
    int rowspan() default 0;

    /**
     * 导出时是否显示边框
     * @return
     */
    boolean border() default false;

    /**
     * 标题栏颜色
     * @return
     */
    String color() default "";

    /**
     * 标题栏颜色
     * @return
     */
    int size() default 11;

    /**
     * 子模块class名称
     * @return
     */
    String childClass() default "";

    /**
     * 从第几行开始
     * @return
     */
    int startColNum() default 0;

}
