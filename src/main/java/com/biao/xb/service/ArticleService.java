package com.biao.xb.service;

import com.biao.xb.dao.ArticleDao;
import com.biao.xb.entity.Article;
import com.biao.xb.entity.PageEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ArticleService {

    private ArticleDao articleDao = new ArticleDao();

    /**
     * 根据文章分页 查询
     * @param currPage
     * @param title
     * @return
     */
    public PageEntity<Article> findPage(Integer currPage, String title) {
        //当前页 总记录数 总页数 查询结果集 每页大小
        PageEntity<Article> pageEntity = new PageEntity<>();
        Integer pageSize = pageEntity.getPageSize();

        //其实索引
        Integer startIndex = (currPage - 1) * pageSize;

        List<Article> data = articleDao.findPage(title,startIndex,pageSize);

        //总记录数
        Integer totalCount = articleDao.countByTitle(title);

        // 总页数
        Integer totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;

        pageEntity.setCurrPage(currPage);
        pageEntity.setData(data);
        pageEntity.setTotalCount(totalCount);
        pageEntity.setTotalPage(totalPage);

        return pageEntity;
    }

    /**
     * 发布文章
     * @param article
     */
    public void add(Article article) {

        article.setBrowseCount(0L); //默认浏览量为0
        article.setPublishDate(new Date()); //当前时间发布

        articleDao.add(article);
    }

    /**
     * 浏览量 + 1
     * @param id
     */
    public void updateBrowserCount(Long id) {
        articleDao.updateBrowserCount(id);
    }

    /**
     * 根据 id 查询  Article 对象
     * @param id
     * @return
     */
    public Article findById(Long id) {
        return articleDao.findById(id);
    }

    /**
     * 收藏次数 favorite
     * @param id
     * @return
     */
    public Integer countFavoriteByArticleId(Long id) {
        return articleDao.countFavoriteByArticleId(id);
    }

    /**
     * 我是否有收藏过这篇文章
     * @param userId
     * @param id
     * @return
     */
    public Boolean isFavorite(Long userId, Long id) {
        Integer count = articleDao.countByUserIdAndArticleId(userId, id);
        return count == 0? false:true;
    }

    /**
     * 我关注的人，并且他们也收藏了这篇文章
     * @param userId
     * @param articleId
     * @return
     */
    public List<Map<String, Object>> findUserFavorite(Long userId, Long articleId) {
        return articleDao.findUserFavorite(userId,articleId);
    }

    /**
     * 关注收藏 (insert)
     * @param userId
     * @param articleId
     */
    public void favorite(Long userId, Long articleId) {
        articleDao.favorite(userId,articleId);
    }

    /**
     * 取消收藏 (delete)
     * @param userId
     * @param articleId
     */
    public void unFavorite(Long userId, Long articleId) {
        articleDao.unFavorite(userId,articleId);
    }

    /**
     * 我的收藏 + 分页
     * @param currPage
     * @param title
     * @param id
     * @return
     */
    public PageEntity<Article> favoritePage(Integer currPage, String title, Long id) {
        //总记录数 总页数 每页大小 查询结果 当前页
        PageEntity<Article> pageEntity = new PageEntity<>();

        Integer pageSize = pageEntity.getPageSize();

        //起始索引
        Integer startIndex = (currPage - 1)  *pageSize;

        //查询结果
        List<Article> data =  articleDao.favoritePage(id, title, startIndex, pageSize);

        // 查询总记录数
        Integer totalCount = articleDao.countByFavorite(id, title);

        //总页数
        Integer totalPage = totalCount % pageSize == 0? totalCount / pageSize : totalCount / pageSize + 1;

        pageEntity.setCurrPage(currPage);
        pageEntity.setData(data);
        pageEntity.setTotalCount(totalCount);
        pageEntity.setTotalPage(totalPage);

        return pageEntity;
    }
}






















