package koBus.mvc.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class FrpsDtlInfHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        JSONArray frpsArray = new JSONArray();

        JSONObject frps1 = new JSONObject();
        frps1.put("frpsDtlCd", "01");
        frps1.put("frpsDtlNm", "���߱�(��~��) 5�ϱ�");
        frpsArray.add(frps1);

        JSONObject frps2 = new JSONObject();
        frps2.put("frpsDtlCd", "02");
        frps2.put("frpsDtlNm", "���ϱ�(��~��) 4�ϱ�");
        frpsArray.add(frps2);

        JSONObject frps3 = new JSONObject();
        frps3.put("frpsDtlCd", "03");
        frps3.put("frpsDtlNm", "�ָ���(��~��) 2�ϱ�");
        frpsArray.add(frps3);

        JSONObject frps4 = new JSONObject();
        frps4.put("frpsDtlCd", "04");
        frps4.put("frpsDtlNm", "���ϱ�(1�ϱ�)");
        frpsArray.add(frps4);

        // JS���� ����ϴ� ������ ����
        JSONObject result = new JSONObject();
        result.put("listCnt", frpsArray.size());
        result.put("frpsDtlList", frpsArray);

        out.print(result.toString());
        out.flush();

        return null;
    }
}
