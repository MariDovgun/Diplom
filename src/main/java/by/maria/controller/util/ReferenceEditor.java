package by.maria.controller.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReferenceEditor {

    private static final Pattern URLRedirectPattern = Pattern.compile("[/](\\w+)[?](.+)");
    private static final String REFERER_PARAM="referer";
    private static final String DEFAULT_REFER="/welcome";

    public static String getReference(HttpServletRequest request){
        String url=request.getHeader(REFERER_PARAM);
        Matcher m=URLRedirectPattern.matcher(url);
        if (m.find()){
            return m.group();
        }
        else {
            return DEFAULT_REFER;
        }
    }
}
