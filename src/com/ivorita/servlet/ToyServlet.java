package com.ivorita.servlet;

import com.google.gson.Gson;
import com.ivorita.bean.ListOfToy;
import com.ivorita.bean.ToysPic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ToyServlet")
public class ToyServlet extends HttpServlet {

    private List<ToysPic> toysPics = new ArrayList<>();
    private String json;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        try {
            ToysPic toysPic1 = new ToysPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557399360755&di=b47647953671b4a414035ece279d11c7&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fphoto%2F20121218%2FRedocn_2012120314142161.jpg",10);
            ToysPic toysPic2 = new ToysPic("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3243231901,1597580347&fm=26&gp=0.jpg",5);
            ToysPic toysPic3 = new ToysPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557399873892&di=358759ce7e4323687bd48953b89225da&imgtype=0&src=http%3A%2F%2Fimg.11665.com%2Fimg04_p%2Fi4%2F12531031399735049%2FT1iizgFfpaXXXXXXXX_%2521%25210-item_pic.jpg",3);
            ToysPic toysPic4 = new ToysPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557399908860&di=c26d7d8af9601f7843ac54c943341a73&imgtype=0&src=http%3A%2F%2Fdpic.tiankong.com%2F1b%2Fc2%2FQJ6947708622.jpg",4);
            ToysPic toysPic5 = new ToysPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557399942142&di=a26ae067f1d58fc198bc936783d55bff&imgtype=0&src=http%3A%2F%2Fimg002.hc360.cn%2Fk3%2FM01%2F0C%2F5E%2FwKhQv1ghgx2EQzxzAAAAAAeOddg276.jpg",20);
            ToysPic toysPic6 = new ToysPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557399360755&di=b47647953671b4a414035ece279d11c7&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fphoto%2F20121218%2FRedocn_2012120314142161.jpg",10);
            ToysPic toysPic7 = new ToysPic("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3243231901,1597580347&fm=26&gp=0.jpg",5);
            ToysPic toysPic8 = new ToysPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557399873892&di=358759ce7e4323687bd48953b89225da&imgtype=0&src=http%3A%2F%2Fimg.11665.com%2Fimg04_p%2Fi4%2F12531031399735049%2FT1iizgFfpaXXXXXXXX_%2521%25210-item_pic.jpg",3);
            ToysPic toysPic9 = new ToysPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557399908860&di=c26d7d8af9601f7843ac54c943341a73&imgtype=0&src=http%3A%2F%2Fdpic.tiankong.com%2F1b%2Fc2%2FQJ6947708622.jpg",4);
            ToysPic toysPic10 = new ToysPic("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557399942142&di=a26ae067f1d58fc198bc936783d55bff&imgtype=0&src=http%3A%2F%2Fimg002.hc360.cn%2Fk3%2FM01%2F0C%2F5E%2FwKhQv1ghgx2EQzxzAAAAAAeOddg276.jpg",20);

            toysPics.add(toysPic1);
            toysPics.add(toysPic2);
            toysPics.add(toysPic3);
            toysPics.add(toysPic4);
            toysPics.add(toysPic5);
            toysPics.add(toysPic6);
            toysPics.add(toysPic7);
            toysPics.add(toysPic8);
            toysPics.add(toysPic9);
            toysPics.add(toysPic10);

            ListOfToy listOfToy = new ListOfToy(toysPics.size(),toysPics);

            Gson gson = new Gson();
            json = gson.toJson(listOfToy);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            toysPics.clear();
        }

        System.out.println("json：" + json);

        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.print(json);
        pw.flush();
    }
}
