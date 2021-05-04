package ru.learn.java.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Используется servlet api 4.0 (до 3.0 не было следующей аннотации WebServlet, вместо неё всё прописывалось в конфигурации web.xml для каждого сервлета
@WebServlet(name = "HiEverybodyServlet", urlPatterns = {"/servlet"})
public class HiEverybodyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.getWriter().println("Servlet web response: HiEverybodyServlet!");
        } finally {
            resp.getWriter().close();
        }
    }

}
