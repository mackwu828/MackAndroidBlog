//package com.mackwu.component.func.excel;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.metadata.data.ReadCellData;
//import com.alibaba.excel.read.listener.ReadListener;
//import com.alibaba.excel.util.ConverterUtils;
//import com.alibaba.excel.util.ListUtils;
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//
///**
// * @author MackWu
// * @since 2023/9/4 17:51
// */
//public class ExcelManager {
//
//    private static ExcelManager instance;
//    /**
//     * 单次缓存的数据量
//     */
//    public static final int BATCH_COUNT = 500;
//    /**
//     * 临时存储
//     */
//    private List<LinkedHashMap<Integer, String>> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//    Map<Integer, String> headerMap;
//
//    private ExcelManager() {
//
//    }
//
//    public static ExcelManager getInstance() {
//        if (instance == null) {
//            instance = new ExcelManager();
//        }
//        return instance;
//    }
//
//    /**
//     * excel转xml
//     *
//     * @param excelFilePath excel文件路径
//     */
//    public void excelToXml(String excelFilePath) {
//        EasyExcel.read(excelFilePath, new ReadListener<LinkedHashMap<Integer, String>>() {
//
//
//            @Override
//            public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
//                headerMap = ConverterUtils.convertToStringMap(headMap, context);
//            }
//
//            @Override
//            public void invoke(LinkedHashMap<Integer, String> data, AnalysisContext context) {
//                cachedDataList.add(data);
//            }
//
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext context) {
////                0 -> {0=null, 1=null, 2=my_frame, 3=My frame, 4=我的相框}
////                1 -> {0=null, 1=null, 2=my_frame_name, 3=Frame name, 4=相框名称}
//                for (int i = 0; i < cachedDataList.size(); i++) {
//                    System.out.println(i + " -> " + cachedDataList.get(i));
//                }
////                generateXml(cachedDataList, xmlFileNames);
//            }
//        }).sheet().doRead();
//    }
//
//    /**
//     * xml转excel
//     *
//     * @param excelFilePath 需要生成的excel文件路径
//     * @param xmlFileNames  源xml文件列表
//     */
//    public static void xml2Excel(String excelFilePath, String... xmlFileNames) {
//        try {
//            System.out.println("===start===");
//            final File excelFile = new File(excelFilePath);
//            if (excelFile.exists()) {
//                excelFile.delete();
//            }
//            final List<HashMap<String, String>> list = new ArrayList<>();
//            for (String xmlFileName : xmlFileNames) {
//                final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                HashMap<String, String> valueMap = parseXml(factory.newDocumentBuilder(), xmlFileName);
//                list.add(valueMap);
//            }
//            final List<LinkedHashMap<Integer, String>> data = convertData(list);
//            System.out.println("first->"+data.get(0));
//            EasyExcel.write(excelFilePath)
////                    .head(heads)
//                    .sheet("翻译")
//                    .doWrite(data);
//            System.out.println("===end===");
//
//        } catch (ParserConfigurationException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static List<LinkedHashMap<Integer, String>> convertData(List<HashMap<String, String>> list) {
//        List<LinkedHashMap<Integer, String>> result = new ArrayList<>();
//        List<String> keyList = new ArrayList<>();
//        for (HashMap<String, String> hashMap : list) {
//            Set<String> strings = hashMap.keySet();
//            for (String key : strings) {
//                if (!keyList.contains(key)) {
//                    keyList.add(key);
//                }
//            }
//        }
//        String value = "";
//        for (String key : keyList) {
//            LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
//            linkedHashMap.put(0, key);
//            for (int i = 1; i < list.size() + 1; i++) {
//                value = list.get(i-1).get(key);
//                if (value == null) {
//                    value = "";
//                }
//                linkedHashMap.put(i, value);
//            }
//            result.add(linkedHashMap);
//        }
//        return result;
//    }
//
//    private static HashMap<String, String> parseXml(DocumentBuilder documentBuilder, String xmlFileName) {
//        try {
//            final File f = new File(xmlFileName);
//            final Document doc = documentBuilder.parse(f);
//            final NodeList nl = doc.getElementsByTagName("string");
//            Node item = null;
//            HashMap<String, String> map = new HashMap<>();
//            for (int i = 0; i < nl.getLength(); i++) {
//                item = nl.item(i);
//                map.put(item.getAttributes().getNamedItem("name").getNodeValue(), item.getFirstChild().getNodeValue());
//            }
//            return map;
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /*
//     <?xml version="1.0" encoding="utf-8"?>
//     <resources>
//        <string name="app_name">建图工具</string>
//    </resources>
//     */
//    private static void generateXml(List<LinkedHashMap<Integer, String>> cachedDataList, String... xmlFileNames) {
//        long startTime = System.currentTimeMillis();
//        System.out.println("===start generate xml file===");
//
//        List<StringBuilder> sbList = ListUtils.newArrayListWithExpectedSize(xmlFileNames.length);
//
//        for (int i = 0; i < xmlFileNames.length; i++) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//            sb.append("\r\n");
//            sb.append("<resources>");
//            sb.append("\r\n");
//            sbList.add(sb);
//        }
//
//        StringBuilder stringBuilder = null;
//        for (LinkedHashMap<Integer, String> linkedHashMap : cachedDataList) {
//            for (int i1 = 1; i1 < linkedHashMap.entrySet().size(); i1++) {
//                stringBuilder = sbList.get(i1 - 1);
//                stringBuilder.append("    ");
//                stringBuilder.append("<string name=\"");
//                stringBuilder.append(linkedHashMap.get(0));
//                stringBuilder.append("\">");
//                stringBuilder.append(linkedHashMap.get(i1));
//                stringBuilder.append("</string>");
//                stringBuilder.append("\r\n");
//            }
//        }
//
//        for (StringBuilder sb : sbList) {
//            sb.append("</resources>");
//        }
//        for (int i = 0; i < sbList.size(); i++) {
//            writeString2File(xmlFileNames[i], sbList.get(i).toString());
//        }
//        System.out.println("generateXml cost time is " + (System.currentTimeMillis() - startTime));
//        System.out.println("===end===");
//    }
//
//    private static void writeString2File(String filePath, String text) {
//        final File file = new File(filePath);
//        FileOutputStream fos = null;
//        try {
//            if (!file.exists()) {
//                boolean createNewFile = file.createNewFile();
//                if (!createNewFile) {
//                    System.out.println("create file error, file path is " + filePath);
//                    return;
//                }
//            }
//            fos = new FileOutputStream(file);
//            fos.write(text.getBytes(StandardCharsets.UTF_8));
//            fos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
