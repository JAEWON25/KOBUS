package koBus.mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import net.sf.json.JSONObject;

public class FrpsVldTermHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();

            // ���� JSON ���� ������
            JSONObject result = new JSONObject();
            result.put("adtnDupPrchYn", "N");
            result.put("vldTermStdt", "2025-06-12");
            result.put("vldTermEddt", "2025-07-12");

            out.print(result.toString());
        } catch (Exception e) {
            e.printStackTrace(); // �ʿ� �� �α׷� ��ü
        }

        return null;
    }
}
