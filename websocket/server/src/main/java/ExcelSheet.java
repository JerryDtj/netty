import java.lang.annotation.*;

/**
 * excel导入/导出 对应的sheet注解。
 *
 * @author jerry
 * @date 2019-07-14 14:36
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelSheet {

	/**
	 * 当前Excel Sheet的名称
	 *
	 * @return
	 */
	String name() default "";

	/**
	 * Excel标题行数
	 *
	 * @return
	 */
	int titleNum() default 0;

	/**
	 * true表示单元格的名称与Domain对象不匹配时抛出异常
	 * 匹配规则如下：entity中所有加入@ExcelCell注解的列 去 excel中匹配标题，如果没有找到，那么会抛出异常。
	 * 
	 * @return
	 */
	boolean check() default false;

	/**
	 * 是否一次性把整个实体类校验值全部给我
	 * 注意：字段名称配置了checkSourceData 后，返回的字段可选值会覆盖这里返回的字段可选值覆盖
	 *
	 * @return
	 */
	boolean isReturnAllData() default false;

	/**
	 * excel 标题字段
	 *
	 * @return
	 */
	String headerName() default "";
}