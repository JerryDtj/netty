import java.sql.Date;
import java.util.List;

/**
 * 划线排版导出模版
 *
 * @Author: jerry
 * @date: 2019/10/21 10:47
 * @description: 排版时间集合：Map<排班日期，List<以30分钟为单位记录的0点到23点的排班情况，有排班请写入16进制颜色如：#000或者#000000，没有为null>>
 **/
@ExcelSheet(name = "划线排班表",titleNum = 2)
public class PaibanLineExportVo {
    @ExcelCellExport(name = "标题",colspan = -1)
    private String titleName = "划线排班表";
    @ExcelCellExport(name = "姓名",rowspan = 2)
    private String userName;
//    公休规则：true 管控， false 不管控
    @ExcelCellExport(name = "公休规则",rowspan = 2)
    private String control;
    @ExcelCellExport(name = "工号",rowspan = 2)
    private String empNo;
    @ExcelCellExport(name = "部门",rowspan = 2)
    private String organName;
    @ExcelCellExport(name = "岗位",rowspan = 2)
    private String gangweiName;
    @ExcelCellExport(name = "应休|已休",rowspan = 2)
    private String yinxiuYixiu;
    @ExcelCellExport(name = "周期已排|周期工时",rowspan = 2)
    private String yipaiAll;
    @ExcelCellExport(name = "周期超时|周期缺时",rowspan = 2)
    private String overLack;
    @ExcelCellExport(name = "周期单位",rowspan = 2)
    private String termUnit;
    @ExcelCellExport(name = "班次时间",rowspan = 2)
    private String banciTime;
    @ExcelCellExport(name = "计薪工时",rowspan = 2)
    private int payrollHours;
//    排版时间集合：Map<排班日期，List<以30分钟为单位记录的0点到23点的排班情况，有排班请写入16进制颜色如：#000或者#000000，没有为null>>
    @ExcelCellExport(childClass = "Pbdate")
    private List<Pbdate> pbdates;

    @ExcelSheet(name = "划线排班表",titleNum = 2)
    public static class Pbdate{
        @ExcelCellExport(name = "排班日期",colspan = -1)
        private Date pbdate;
        @ExcelCellExport(name = "排班时间",colspan = 2,startColNum = 1)
        private List<String> times;

        public Date getPbdate() {
            return pbdate;
        }

        public void setPbdate(Date pbdate) {
            this.pbdate = pbdate;
        }

        public List<String> getTimes() {
            return times;
        }

        public void setTimes(List<String> times) {
            this.times = times;
        }
    }


    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getControl() {
        return control;
    }

    public void setControl(boolean control) {
        if (control){
            this.control = "管控";
        }else {
            this.control = "不管控";
        }
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getGangweiName() {
        return gangweiName;
    }

    public void setGangweiName(String gangweiName) {
        this.gangweiName = gangweiName;
    }

    public String getYinxiuYixiu() {
        return yinxiuYixiu;
    }

    public void setYinxiuYixiu(String yinxiuYixiu) {
        this.yinxiuYixiu = yinxiuYixiu;
    }

    public String getYipaiAll() {
        return yipaiAll;
    }

    public void setYipaiAll(String yipaiAll) {
        this.yipaiAll = yipaiAll;
    }

    public String getOverLack() {
        return overLack;
    }

    public void setOverLack(String overLack) {
        this.overLack = overLack;
    }

    public String getTermUnit() {
        return termUnit;
    }

    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }

    public String getBanciTime() {
        return banciTime;
    }

    public void setBanciTime(String banciTime) {
        this.banciTime = banciTime;
    }

    public int getPayrollHours() {
        return payrollHours;
    }

    public void setPayrollHours(int payrollHours) {
        this.payrollHours = payrollHours;
    }

    public List<Pbdate> getPbdates() {
        return pbdates;
    }

    public void setPbdates(List<Pbdate> pbdates) {
        this.pbdates = pbdates;
    }
}
