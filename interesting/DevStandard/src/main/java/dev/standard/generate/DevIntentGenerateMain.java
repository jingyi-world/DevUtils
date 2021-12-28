package dev.standard.generate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dev.utils.DevFinal;
import dev.utils.common.StringUtils;

/**
 * detail: DevIntent 类创建
 * @author Ttt
 * <pre>
 *     专门用于根据 {@link DevFinal.STR} 常量
 *     生成 DevAssist 库中, dev.base 包下
 *     DevIntent 类 ( DevFinal.STR Intent 传参读写辅助类 )
 * </pre>
 */
public class DevIntentGenerateMain {

    // 方法字符串
    private static final String METHOD_STR;

    static {
        StringBuilder builder = new StringBuilder();
        builder.append("\n    // =");
        builder.append("\n");
        builder.append("\n    /**");
        builder.append("\n     * 获取 Key ( %s ) 对应的 Value");
        builder.append("\n     * @return Value");
        builder.append("\n     */");
        builder.append("\n    public String get%s() {");
        builder.append("\n        return get(DevFinal.STR.%s);");
        builder.append("\n    }");
        builder.append("\n");
        builder.append("\n    /**");
        builder.append("\n     * 设置 Key ( %s ) 对应的 Value");
        builder.append("\n     * @param value 保存的 value");
        builder.append("\n     * @return {@link DevIntent}");
        builder.append("\n     */");
        builder.append("\n    public DevIntent set%s(final String value) {");
        builder.append("\n        return put(DevFinal.STR.%s, value);");
        builder.append("\n    }");
        builder.append("\n");
        builder.append("\n    /**");
        builder.append("\n     * 移除 Key ( %s )");
        builder.append("\n     * @return {@link DevIntent}");
        builder.append("\n     */");
        builder.append("\n    public DevIntent remove%s() {");
        builder.append("\n        return remove(DevFinal.STR.%s);");
        builder.append("\n    }");
        builder.append("\n");
        builder.append("\n    /**");
        builder.append("\n     * 是否存在 Key ( %s )");
        builder.append("\n     * @return {@code true} yes, {@code false} no");
        builder.append("\n     */");
        builder.append("\n    public boolean contains%sKey() {");
        builder.append("\n        return containsKey(DevFinal.STR.%s);");
        builder.append("\n    }");
        METHOD_STR = builder.toString();
    }

    public static void main(String[] args) {
        // 循环所有常量
        List<String> lists  = new ArrayList<>();
        Field[]      fields = DevFinal.STR.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String name      = StringUtils.upperFirstLetter(fieldName.toLowerCase());
            lists.add(name);
        }

        // ==========
        // = 开始拼接 =
        // ==========

        StringBuilder builder = new StringBuilder();
        for (String name : lists) {
            String finalName     = StringUtils.underScoreCaseToCamelCase(name);
            String nameUpperCase = name.toUpperCase();

            // class 特殊处理防止 getClass
            if (finalName.equalsIgnoreCase("class")) {
                finalName = finalName.toUpperCase();
            }

            builder.append(DevFinal.SYMBOL.NEW_LINE)
                    .append(String.format(
                            METHOD_STR,
                            nameUpperCase, finalName, nameUpperCase,
                            nameUpperCase, finalName, nameUpperCase,
                            nameUpperCase, finalName, nameUpperCase,
                            nameUpperCase, finalName, nameUpperCase
                    ));
        }
        // 最终内容
        String content = builder.toString();

        String debug = "";
    }
}