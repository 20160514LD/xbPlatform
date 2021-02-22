package com.biao.xb.dao;

import com.biao.xb.entity.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;
import java.util.Map;

public class ArticleDao extends BaseDao {
    /**
     * 根据文章分页查询
     * @param title
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<Article> findPage(String title, Integer startIndex, Integer pageSize) {
        try {
            String sql = "select * from article where title like ? order by publish_date desc limit ?,?";
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Article.class),"%" + title + "%",startIndex,pageSize);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询总记录数
     * @param title
     * @return
     */
    public Integer countByTitle(String title) {
        try {
            return jdbcTemplate.queryForObject("select count(1) from article where title like ?",Integer.class,"%" + title + "%");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发布文章
     * @param article
     */
    public void add(Article article) {
        try {
            jdbcTemplate.update("insert into article values(null,?,?,?,?,?,?)",
                    article.getTitle(),
                    article.getContent(),
                    article.getBrowseCount(),
                    article.getPublishDate(),
                    article.getPublishRealName(),
                    article.getUserId());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 浏览量 + 1
     * @param id
     */
    public void updateBrowserCount(Long id) {
        try {
            jdbcTemplate.update("update article set browse_count = browse_count+1 where id = ?",id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据 id 查询  Article 对象
     * @param id
     * @return
     */
    public Article findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from article where id = ?",new BeanPropertyRowMapper<>(Article.class),id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 收藏次数 favorite
     * @param id
     * @return
     */
    public Integer countFavoriteByArticleId(Long id) {
        try {
            return jdbcTemplate.queryForObject("select count(1) from favorite where a_id=?",Integer.class,id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 我是否有收藏过这篇文章
     * @param userId
     * @param id
     * @return
     */
    public Integer countByUserIdAndArticleId(Long userId, Long id) {
        try {
            return jdbcTemplate.queryForObject("select count(1) from favorite where u_id=? and a_id=?",Integer.class,userId,id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 我关注的人，并且他们也收藏了这篇文章
     * @param userId
     * @param articleId
     * @return
     */
    public List<Map<String, Object>> findUserFavorite(Long userId, Long articleId) {
        String sql="        select u.id userId,u.real_name realName from userfocus uf\n" +
                "        left join user u on u.id=uf.user_focus_id\n" +
                "        left join favorite f on f.u_id=u.id\n" +
                "        where uf.user_id=? and f.a_id=?";
        return jdbcTemplate.queryForList(sql, userId, articleId);
    }

    /**
     * 关注收藏 (insert)
     * @param userId
     * @param articleId
     */
    public void favorite(Long userId, Long articleId) {
        try {
            jdbcTemplate.update("insert into favorite values(?,?) ",userId,articleId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 取消收藏 (delete)
     * @param userId
     * @param articleId
     */
    public void unFavorite(Long userId, Long articleId) {
        try {
            jdbcTemplate.update("delete from favorite where u_id=? and a_id=? ",userId,articleId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 我的收藏  + 分页
     * @param id
     * @param title
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<Article> favoritePage(Long id, String title, Integer startIndex, Integer pageSize) {
        try {
            return jdbcTemplate.query(
                    "select a.* from favorite f left join article a on f.a_id=a.id where f.u_id=? and title like ?  order by publish_date desc limit ?,?",
                    new BeanPropertyRowMapper<>(Article.class),
                    id, "%" + title + "%", startIndex, pageSize);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据用户 id 和文章标题查询总记录数
     * @param id
     * @param title
     * @return
     */
    public Integer countByFavorite(Long id, String title) {
        try {
            return jdbcTemplate.queryForObject(
                    "select count(1) from favorite f left join article a on f.a_id=a.id where f.u_id=? and title like ?",
                    Integer.class, id, "%" + title + "%");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}























