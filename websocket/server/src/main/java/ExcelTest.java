import java.lang.reflect.Field;
import java.util.Objects;

/**
 * despaction
 *
 * @Author: jerry
 * @date: 2019/11/4 19:48
 * @description:
 **/

public class ExcelTest {
    public static void main(String[] args) {
        PaibanLineExportVo vo = new PaibanLineExportVo();
        Class<?> clazz = vo.getClass();
        int count = 0;
        Field[] fields = clazz.getDeclaredFields();
        Field.setAccessible(fields, true);
        for (Field field:fields){
            ExcelCellExport excelCellExport=field.getAnnotation(ExcelCellExport.class);
            if (Objects.nonNull(excelCellExport)&&Objects.isNull(excelCellExport.childClass())){
                count++;
            }
        }
        System.out.println(count);
    }

    public void export(){

    }

}
