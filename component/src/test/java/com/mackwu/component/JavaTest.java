package com.mackwu.component;

import com.mackwu.component.func.excel.ExcelManager;

import org.junit.Test;

/**
 * @author MackWu
 * @since 2023/6/16 14:57
 */
public class JavaTest {

    @Test
    public void a() {
//        Utils.excelToXml("C:\\Users\\MSI-NB\\Desktop\\Whale Photo 相框端.xlsx");
        ExcelManager.getInstance().excelToXml("C:\\Users\\MSI-NB\\Desktop\\test.xlsx");
    }

}
