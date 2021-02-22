package com.biao.xb.controller;

import com.biao.xb.entity.Article;
import com.biao.xb.entity.PageEntity;
import com.biao.xb.service.ArticleService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet {

    private ArticleService articleService = new ArticleService();

    /**
     * 查询文章分页
     * @param request
     * @param response
     * @throws Exception
     */
    public void findPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> param = getParam(request);
        Integer currPage = Integer.parseInt(param.get("currPage"));
        String title = param.get("title");

        Long userId = loginUser.getId();
        //分页查询文章信息
        PageEntity<Article> pageData = articleService.findPage(currPage, title);
        //设置域中的返回值
        request.setAttribute("pageData",pageData);
        request.setAttribute("title",title);
        //跳转页面
        request.getRequestDispatcher("/html/article.jsp").forward(request,response);
    }

    /**
     * 发布文章
     * @param request
     * @param response
     * @throws Exception
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Article article = new Article();
        BeanUtils.populate(article,request.getParameterMap());

        //设置发布信息
        article.setUserId(loginUser.getId());
        article.setPublishRealName(loginUser.getRealName());

        articleService.add(article);

        //设置重定向到 findPage 中填充数据
        response.sendRedirect("/article/findPage?currPage=1");
    }

    /**
     * 文章详情
     * @param request
     * @param response
     * @throws Exception
     */
    public void articleDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {

        /**
         * 文章浏览量 + 1
         * Article 对象
         * 收藏次数
         * 我是否有收藏过这篇文章
         * 我关注的人，并且他们也收藏了这篇文章（List<User>）
         */

        Map<String, String> param = getParam(request);
        Long id =  Long.parseLong(param.get("id"));
        //浏览量 + 1
        articleService.updateBrowserCount(id);
        //查询 Article 对象
        Article article = articleService.findById(id);

        //收藏次数 favorite
        Integer favoriteCount = articleService.countFavoriteByArticleId(id);

        //我是否有收藏过这篇文章
        Boolean isFavorite = articleService.isFavorite(loginUser.getId(),id);

        //我关注的人，并且他们也收藏了这篇文章
        List<Map<String, Object>> userFavorite  = articleService.findUserFavorite(loginUser.getId(),id);

        request.setAttribute("article", article);
        request.setAttribute("isFavorite", isFavorite);
        request.setAttribute("userFavorite", userFavorite);
        request.setAttribute("favoriteCount", favoriteCount);

        request.getRequestDispatcher("/html/article_detail.jsp").forward(request, response);
    }

    /**
     * 收藏文章/取消收藏 (ajax)
     * @param request
     * @param response
     * @throws Exception
     */
    public void favorite(HttpServletRequest request,HttpServletResponse response) throws Exception {
        /**
         * 文章id  是否收藏 当前用户的id
         */
        try {
            Map<String, String> param = getParam(request);
            Long articleId = Long.parseLong(param.get("id"));

            Boolean isFavorite  = Boolean.parseBoolean(param.get("isFavorite"));
            Long userId = loginUser.getId();

            if (!isFavorite) {
                //若 isFavorite 为 false，则没有 收藏，则关注收藏 (insert)
                articleService.favorite(userId, articleId);
            }else {
                //若 isFavorite 为 true，则关注收藏了，则取消收藏 (delete)
                articleService.unFavorite(userId, articleId);
            }
            writeObjectToString(response, 200);
        }catch (Exception e) {
            writeObjectToString(response, 500);
            e.printStackTrace();
        }
    }

    /**
     * 我的收藏 + 分页
     * @param request
     * @param response
     * @throws Exception
     */
    public void favoritePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> param = getParam(request);
        Integer currPage = Integer.parseInt(param.get("currPage"));
        String title = param.get("title");

        PageEntity<Article> pageData = articleService.favoritePage(currPage, title,loginUser.getId());

        request.setAttribute("pageData",pageData);

        request.getRequestDispatcher("/html/article_collect.jsp").forward(request,response);
    }

}






























