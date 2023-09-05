package com.mackwu.component.util;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Locale;

public class SwMaker {

    private int designWidth;
    private int[] dpiValues;
    private int[] swValues;
    private int dpMin;
    private int dpMax;
    private int spMin;
    private int spMax;
    private String prefix;

    public SwMaker() {
        this(new Builder());
    }

    public SwMaker(Builder builder) {
        this.designWidth = builder.designWidth;
        this.swValues = builder.swValues;
        this.dpMin = builder.dpMin;
        this.dpMax = builder.dpMax;
        this.spMin = builder.spMin;
        this.spMax = builder.spMax;
        this.prefix = builder.prefix;
    }

    public static final class Builder {
        private int designWidth;
        private String moduleName;
        private int[] dpiValues;
        private int[] swValues;
        private int dpMin;
        private int dpMax;
        private int spMin;
        private int spMax;
        private String prefix;

        public Builder() {
            this.designWidth = 1080;
            this.moduleName = "app";
            this.dpiValues = new int[]{120, 160, 213, 240, 260, 280, 300, 320, 340, 360, 400, 420, 440, 450, 480, 560, 640};
            this.swValues = calculateSw();
            this.dpMin = -50;
            this.dpMax = 1920;
            this.spMin = 0;
            this.spMax = 120;
            this.prefix = "";
        }

        public Builder designWidth(int designWidth) {
            this.designWidth = designWidth;
            return this;
        }

        public Builder dpiValues(int[] dpiValues) {
            this.dpiValues = dpiValues;
            return this;
        }

        public Builder swValues(int[] swValues) {
            this.swValues = swValues;
            return this;
        }

        public Builder calculateSwByDpi() {
            this.swValues = calculateSw();
            return this;
        }

        public Builder dpMin(int dpMin) {
            this.dpMin = dpMin;
            return this;
        }

        public Builder dpMax(int dpMax) {
            this.dpMax = dpMax;
            return this;
        }

        public Builder spMax(int spMax) {
            this.spMax = spMax;
            return this;
        }

        public Builder prefix(String prefix) {
            if (!prefix.isEmpty()) {
                this.prefix = prefix + "_";
            }
            return this;
        }

        public SwMaker build() {
            return new SwMaker(this);
        }


        /**
         * 计算最小宽度。
         * px=dp/(dpi/160)
         * 1dp=(dpi/160)px => 1dp对应(dpi/160)px
         * 最小宽度=屏幕宽度/(dpi/160)
         */
        public int[] calculateSw() {
            int[] swValues = new int[dpiValues.length];
            for (int i = 0; i < dpiValues.length; i++) {
                int swValue = (int) (designWidth / (dpiValues[i] / 160f));
                swValues[i] = swValue;
//            System.out.println("swValue: " + swValue);
            }
            return swValues;
        }
    }

    /**
     * 生成所有dimens.xml文件
     */
    public void makeAllDimens() {
        try {
            for (int swValue : swValues) {
                // modulePath
                String modulePath = new File("").getCanonicalPath() + "/src/main/res";
                System.out.println(modulePath);
                // dirPath
                String dirPath = modulePath + "/values-sw" + swValue + "dp";
                File dirFile = new File(dirPath);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }

                //
                FileOutputStream fos = new FileOutputStream(dirPath + "/dimens.xml");
                StringBuilder sb = new StringBuilder();
                sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n")
                        .append("<resources>\r\n")
                        .append(String.format(Locale.getDefault(), "   <string name=\"" + prefix + "design_width\">%dpx</string>\r\n", designWidth))
                        .append(String.format(Locale.getDefault(), "   <string name=\"" + prefix + "current_sw\">%ddp</string>\r\n", swValue));

                // dp
                for (int i = dpMin; i <= dpMax; i++) {
                    float dpValue = i / (float) designWidth * swValue;
                    BigDecimal bigDecimal = new BigDecimal(dpValue);
                    bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP);
                    if (i < 0) {
                        sb.append(String.format(Locale.getDefault(), "   <dimen name=\"_" + prefix + "dp_%2$d\">%3$.4fdp</dimen>\r\n", "", i * -1, dpValue));
                    } else {
                        sb.append(String.format(Locale.getDefault(), "   <dimen name=\"" + prefix + "dp_%2$d\">%3$.4fdp</dimen>\r\n", "", i, dpValue));
                    }
                }

                // sp
                for (int i = spMin; i <= spMax; i++) {
                    float spValue = i / (float) designWidth * swValue;
                    BigDecimal bigDecimal = new BigDecimal(spValue);
                    bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP);
                    sb.append(String.format(Locale.getDefault(), "   <dimen name=\"" + prefix + "sp_%2$d\">%3$.4fsp</dimen>\r\n", "", i, spValue));
                }

                sb.append("</resources>\r\n");

                fos.write(sb.toString().getBytes());
                fos.flush();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
