package com.thenam.project2server.services.AdminServices;

import com.thenam.project2server.dto.ArticleDTO;
import com.thenam.project2server.dto.UserDTO;
import com.thenam.project2server.entity.ArticleEntity;
import com.thenam.project2server.entity.PageSourceEntity;
import com.thenam.project2server.entity.UserEntity;
import com.thenam.project2server.repositories.ArticleRepository;
import com.thenam.project2server.repositories.PageSourceRepository;
import com.thenam.project2server.repositories.UserRepository;
import com.thenam.project2server.utils.ExcelHandlingUtils;
import com.thenam.project2server.utils.MapperUtils;
import com.thenam.project2server.utils.ReadHTMLDomUtil;
import com.thenam.project2server.utils.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminServicesImp implements AdminServices {

    @Autowired
    UserRepository userRepo;

    @Autowired
    ExcelHandlingUtils excelHandlingUtils;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    PageSourceRepository pageSourceRepository;

    @Autowired
    ReadHTMLDomUtil readHTMLDomUtil;

    @Autowired
    StringUtils stringUtil;

    @Override
    public List<UserDTO> showAllUser() {
        List<UserEntity> allUsersEntity = userRepo.findAll();
        List<UserDTO> allUsers = MapperUtils.mapAll(allUsersEntity, UserDTO.class);

        return allUsers;
    }

    @Override
    public List<PageSourceEntity> showAllPageSource(){
        List<PageSourceEntity> allPage = pageSourceRepository.findAll();
        return allPage;
    }

    // read DOM of each url to make an article entity, then save to db
    @Override
    public void handleUploadNewsList(String filePath, int pageId){
        Map<Integer, List<String>> data = excelHandlingUtils.excelHandling(filePath);
        System.out.println("thenam: data size" + data.size());

        switch (pageId) {
            case 1:
                for(Map.Entry<Integer, List<String>> entry: data.entrySet()){

                    if(entry.getKey() > 1){
                        String url = entry.getValue().get(0);
                        Document doc = readHTMLDomUtil.handleReadHTML(url);
                        System.out.println("uploading...");
                        if(doc != null){
                            ArticleEntity articleEntity = getInfoArticle(
                                    doc,
                                    url,
                                    pageId,
                                    "img.news-image",
                                    "h1#article_title",
                                    "h2#article_sapo"
                            );
                            if(articleEntity != null){
                                articleRepository.save(articleEntity);
                            }
                        }
                    }
                }
                break;

            case 2:
                for(Map.Entry<Integer, List<String>> entry: data.entrySet()){

                    if(entry.getKey() > 1){
                        String url = entry.getValue().get(0);
                        Document doc = readHTMLDomUtil.handleReadHTML(url);
                        System.out.println("uploading...");
                        if(doc != null){
                            ArticleEntity articleEntity = getInfoArticle(
                                    doc,
                                    url,
                                    pageId,
                                    "img[data-content-name=article-content-image]",
                                    "h1.title-page",
                                    "h2.singular-sapo"
                            );
                            if(articleEntity != null){
                                articleRepository.save(articleEntity);
                            }
                        }
                    }
                }
                break;

            case 3:
                for(Map.Entry<Integer, List<String>> entry: data.entrySet()){

                    if(entry.getKey() > 1){
                        String url = entry.getValue().get(0);
                        Document doc = readHTMLDomUtil.handleReadHTML(url);
                        System.out.println("uploading...");
                        if(doc != null){
                            ArticleEntity articleEntity = getInfoArticle(
                                    doc,
                                    url,
                                    pageId,
                                    ".box-detail-thumb img",
                                    "h1.box-title-detail",
                                    ".box-des-detail p"
                            );
                            if(articleEntity != null){
                                articleRepository.save(articleEntity);
                            }
                        }
                    }
                }
        }
    }

    // read DOM then get information
    private ArticleEntity getInfoArticle(Document doc, String url, int pageId, String imgSelector, String titleSelector, String subContentSelector){
        ArticleEntity articleEntity = new ArticleEntity();
        Element imgSrc = doc.select(imgSelector).first();
        Element title = doc.select(titleSelector).first();
        Element subContent = doc.select(subContentSelector).first();

        if(imgSrc != null && !stringUtil.isBlank(imgSrc.attr("src"))){
            articleEntity.setSrcImage(imgSrc.attr("src"));
        }else{
            return null;
        }

        if(title != null && !stringUtil.isBlank(title.text())){
            articleEntity.setTitle(title.text());
        }else{
            return null;
        }

        if(subContent != null && !stringUtil.isBlank(subContent.text())){
            articleEntity.setSubContent(subContent.text());
        }else{
            return null;
        }
        if(getArticleTopicID(pageId, url) != 0){
            articleEntity.setTopicId(getArticleTopicID(pageId, url));
        }
        else return null;

        articleEntity.setLink(url);
        articleEntity.setPageSourceId(pageId);

        return articleEntity;
    }

    private int getArticleTopicID(int pageId, String url ){
        switch (pageId){
            case 1:
                if(url.contains("cong-nghe-thong-tin") || url.contains("phan-mem-ngoai") || url.contains("thoi-trang-hi-tech"))
                    return 1;
                if(url.contains("giai-tri"))
                    return 2;
                if(url.contains("doanh-nhan") || url.contains("tai-chinh")
                        || url.contains("bat-dong-san")
                        || url.contains("doanh-nghiep")
                        || url.contains("thi-truong-tieu-dung")
                        || url.contains("kinh-doanh")
                )
                    return 3;
                if(url.contains("giao-duc-du-hoc"))
                    return 4;
                if(url.contains("an-ninh-hinh-su"))
                    return 5;
                if(url.contains("suc-khoe-doi-song"))
                    return 6;
                if(url.contains("the-thao") || url.contains("bong-da"))
                    return 7;
                if(url.contains("tin-tuc-quoc-te"))
                    return 8;
                break;

            case 2:
                if(url.contains("suc-manh-so")) return 1;
                if(url.contains("giai-tri")) return 2;
                if(url.contains("kinh-doanh")) return 3;
                if(url.contains("giao-duc-huong-nghiep")) return 4;
                if(url.contains("phap-luat")) return 5;
                if(url.contains("suc-khoe")) return 6;
                if(url.contains("the-thao")) return 7;
                if(url.contains("the-gioi")) return 8;

            case 3:
                if(url.contains("science-news")) return 1;
//                if(url.contains("giai-tri")) return 2;
                if(url.contains("tin-tuc-kinh-te")) return 3;
                if(url.contains("tin-tuc-giao-duc")) return 4;
                if(url.contains("thoi-su-phap-luat")) return 5;
                if(url.contains("tin-tuc-y-te")) return 6;
                if(url.contains("tin-tuc-sea-games-31") || url.contains("the-thao")) return 7;
                if(url.contains("tin-tuc-the-gioi")) return 8;

            default:
                return 0;
        }
        return 0;
    }
}
