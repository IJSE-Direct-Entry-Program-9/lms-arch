package lk.ijse.dep9.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "cors-filter", urlPatterns = {"/members/*", "/books/*"})
public class CorsFilter extends HttpFilter {

    private final String[] origins = {"http://localhost", "http://34.100.220.186:8080"};

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String requestedOrigin = req.getHeader("Origin");
        for (String origin : origins) {
            if (requestedOrigin.startsWith(origin)){
                res.setHeader("Access-Control-Allow-Origin", requestedOrigin);
                break;
            }
        }

        if (req.getMethod().equalsIgnoreCase("OPTIONS")){
            /* Let's handle pre-flighted requests */
            res.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, HEAD, POST, PATCH, DELETE");

            String requestedMethod = req.getHeader("Access-Control-Request-Method");
            String requestedHeaders = req.getHeader("Access-Control-Request-Headers").toLowerCase();

            if ((requestedMethod.equalsIgnoreCase("POST") ||
                requestedMethod.equalsIgnoreCase("PATCH")) &&
                requestedHeaders.contains("content-type")){
                res.setHeader("Access-Control-Allow-Headers", "Content-Type");
            }
        }else{
            if (req.getMethod().equalsIgnoreCase("GET") ||
                    req.getMethod().equalsIgnoreCase("HEAD")){
                res.setHeader("Access-Control-Expose-Headers", "X-Total-Count");
            }
        }
        chain.doFilter(req, res);
    }
}
