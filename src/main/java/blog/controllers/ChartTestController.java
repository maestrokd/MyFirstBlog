package blog.controllers;

import blog.chart.Ponto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ChartTestController {

    @GetMapping(path = "/charts")
    public ModelAndView getCharts() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(10, 71));
        pontos.add(new Ponto(20, 55));
        pontos.add(new Ponto(30, 50));
        pontos.add(new Ponto(40, 65));
        pontos.add(new Ponto(50, 71));
        pontos.add(new Ponto(60, 68));
        pontos.add(new Ponto(70, 38));
        pontos.add(new Ponto(80, 92, "Highest"));
        pontos.add(new Ponto(90, 54));
        pontos.add(new Ponto(100, 60));
        pontos.add(new Ponto(110, 21));
        pontos.add(new Ponto(120, 49));
        pontos.add(new Ponto(130, 36));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pontos",pontos);
        modelAndView.setViewName("charts/charts");
        return modelAndView;
    }
}
