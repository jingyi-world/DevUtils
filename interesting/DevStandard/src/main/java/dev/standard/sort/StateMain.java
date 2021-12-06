package dev.standard.sort;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dev.utils.DevFinal;
import dev.utils.common.FileIOUtils;
import dev.utils.common.StringUtils;

/**
 * detail: State 类创建
 * @author Ttt
 */
public class StateMain {

    // 开头字符串
    private static final String START_GET_STR;
    private static final String START_SET_STR;
    // is 方法字符串
    private static final String IS_METHOD_STR;
    // set 方法字符串
    private static final String SET_METHOD_STR;

    static {
        StringBuilder startGetBuilder = new StringBuilder();
        startGetBuilder.append("\n    // =======");
        startGetBuilder.append("\n    // = get =");
        startGetBuilder.append("\n    // =======");
        START_GET_STR = startGetBuilder.toString();

        StringBuilder startSetBuilder = new StringBuilder();
        startSetBuilder.append("\n    // =======");
        startSetBuilder.append("\n    // = set =");
        startSetBuilder.append("\n    // =======");
        START_SET_STR = startSetBuilder.toString();

        StringBuilder isMethodBuilder = new StringBuilder();
        isMethodBuilder.append("\n    /**");
        isMethodBuilder.append("\n     * 判断是否%s");
        isMethodBuilder.append("\n     * @return {@code true} yes, {@code false} no");
        isMethodBuilder.append("\n     */");
        isMethodBuilder.append("\n    public boolean is%s() {");
        isMethodBuilder.append("\n        return equalsState(DevFinal.INT.%s);");
        isMethodBuilder.append("\n    }");
        IS_METHOD_STR = isMethodBuilder.toString();

        StringBuilder setMethodBuilder = new StringBuilder();
        setMethodBuilder.append("\n    /**");
        setMethodBuilder.append("\n     * 设置状态为%s");
        setMethodBuilder.append("\n     * @return {@link %s}");
        setMethodBuilder.append("\n     */");
        setMethodBuilder.append("\n    public %s<T> set%s() {");
        setMethodBuilder.append("\n        return setState(DevFinal.INT.%s);");
        setMethodBuilder.append("\n    }");
        SET_METHOD_STR = setMethodBuilder.toString();
    }

    public static void main(String[] args) {
        // 循环所有常量
        Map<String, String> maps   = new HashMap<>();
        List<String>        lists  = new ArrayList<>();
        Field[]             fields = StateMain.class.getDeclaredFields();
        for (Field field : fields) {
            // 属于 Int 类型才处理
            if (field.getType().toString().equals("int")) {
                String name = StringUtils.upperFirstLetter(field.getName().toLowerCase());
                lists.add(name);

                String descriptor = Modifier.toString(field.getModifiers());
                descriptor = StringUtils.isEmpty(descriptor) ? "" : descriptor + " ";
                maps.put(name, descriptor + "int " + field.getName());
            }
        }
        // 获取该文件路径
        String filePath = "\\interesting\\DevStandard\\src\\main\\java\\dev\\standard\\sort\\StateMain.java";
        File   file     = new File(System.getProperty("user.dir"), filePath);
        // 读取该文件内容
        List<String> textLists = FileIOUtils.readFileToList(file, DevFinal.ENCODE.UTF_8);

        // ==============
        // = 移除多余代码 =
        // ==============

        String           first           = lists.get(0);
        String           firstDescriptor = maps.get(first);
        Iterator<String> iterator        = textLists.iterator();
        while (iterator.hasNext()) {
            String text = iterator.next();
            if (text.contains(firstDescriptor)) {
                break;
            }
            iterator.remove();
        }

        // ==========
        // = 开始拼接 =
        // ==========

        // return 返回类名
        String className = "CommonState";
//        String className = "RequestState";
        // get 片段字符串拼接
        StringBuilder getPartBuilder = new StringBuilder();
        // set 片段字符串拼接
        StringBuilder setPartBuilder = new StringBuilder();

        getPartBuilder
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append(START_GET_STR);

        setPartBuilder
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append(START_SET_STR);
        // 循环变量名
        for (String name : lists) {
            if (name.equalsIgnoreCase("base")) continue;
            if (name.contains("_BASE")) continue;
            if (name.contains("_base")) continue;
            // 获取注释
            String annotation = null;
            String descriptor = maps.get(name);
            for (int i = 0, len = textLists.size(); i < len; i++) {
                String context = textLists.get(i);
                if (context.contains(descriptor)) {
                    annotation = StringUtils.clearSpaceTabLineTrim(
                            textLists.get(i - 1)
                    );
                    annotation = StringUtils.clearStartsWith(annotation, "// ");
                }
            }
            String finalName = StringUtils.underScoreCaseToCamelCase(name);

            getPartBuilder
                    .append(DevFinal.SYMBOL.NEW_LINE)
                    .append(String.format(
                            IS_METHOD_STR, annotation,
                            finalName, name.toUpperCase()
                    ));

            setPartBuilder
                    .append(DevFinal.SYMBOL.NEW_LINE)
                    .append(String.format(
                            SET_METHOD_STR, annotation,
                            className, className,
                            finalName, name.toUpperCase()
                    ));
        }
        // 最终内容
        String content = getPartBuilder.append(setPartBuilder).toString();

        String debug = "";
    }
}