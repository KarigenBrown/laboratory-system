package edu.bistu.controller;


import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebAchievement;
import edu.bistu.service.WebAchievementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 论文管理(WebAchievement)表控制层
 *
 * @author makejava
 * @since 2024-03-20 19:56:35
 */
@Tag(name = "成就控制器", description = "除去项目，包含论文、专利、著作、软著、技术标准、竞赛获奖")
@RestController
@RequestMapping("/webAchievement")
public class WebAchievementController {
    /**
     * 服务对象
     */
    @Autowired
    private WebAchievementService webAchievementService;

    @Operation(summary = "查找所有未被隐藏且不是草稿的论文")
    @GetMapping("/article/all")
    public Response<List<WebAchievement>> getAllArticles() {
        return Response.ok(webAchievementService.lambdaQuery()
                .eq(WebAchievement::getCategory, "论文")
                .ne(WebAchievement::getArticleStatus, "草稿")
                .eq(WebAchievement::getHidden, 0)
                .list());
    }

    @Operation(summary = "根据年份查找所有未被隐藏且不是草稿的论文")
    @Parameter(name = "years", description = "年份,格式为yyyy-yyyy,初始年份-结束年份")
    @GetMapping("/article/year/{years}")
    public Response<List<WebAchievement>> getArticlesByYear(@PathVariable("years") String years) {
        String[] splitYears = years.split("-");
        int begin = Integer.parseInt(splitYears[0]);
        int end = Integer.parseInt(splitYears[1]);
        return Response.ok(webAchievementService.lambdaQuery()
                .eq(WebAchievement::getCategory, "论文")
                .ne(WebAchievement::getArticleStatus, "草稿")
                .eq(WebAchievement::getHidden, 0)
                .list()
                .stream()
                .filter(webAchievement -> {
                    int year = Integer.parseInt(webAchievement.getDate().substring(0, 4));
                    return begin <= year && year <= end;
                }).toList());
    }

    @Operation(summary = "根据期刊或会议查找所有未被隐藏且不是草稿的论文")
    @GetMapping("/article/journal/{journal}")
    public Response<List<WebAchievement>> getArticlesByJournal(@PathVariable("journal") String journal) {
        return Response.ok(webAchievementService.lambdaQuery()
                .eq(WebAchievement::getCategory, "论文")
                .ne(WebAchievement::getArticleStatus, "草稿")
                .eq(WebAchievement::getHidden, 0)
                .eq(WebAchievement::getJournal, journal)
                .list());
    }

    @Operation(summary = "根据首字母查找所有未被隐藏且不是草稿的论文")
    @GetMapping("/article/initials/{initials}")
    public Response<List<WebAchievement>> getArticlesByInitials(@PathVariable("initials") String initials) {
        return Response.ok(webAchievementService.lambdaQuery()
                .eq(WebAchievement::getCategory, "论文")
                .ne(WebAchievement::getArticleStatus, "草稿")
                .eq(WebAchievement::getHidden, 0)
                .eq(WebAchievement::getInitials, initials)
                .list());
    }

    @Operation(summary = "根据作者(查询范围包括第一作者以及其他作者)查找所有未被隐藏且不是草稿的论文")
    @GetMapping("/article/author/{author}")
    public Response<List<WebAchievement>> getArticlesByAuthor(@PathVariable("author") String author) {
        return Response.ok(webAchievementService.lambdaQuery()
                .eq(WebAchievement::getCategory, "论文")
                .ne(WebAchievement::getArticleStatus, "草稿")
                .eq(WebAchievement::getHidden, 0)
                .eq(WebAchievement::getAuthor, author)
                .or()
                .like(WebAchievement::getAuthors, author)
                .list());
    }

    @Operation(summary = "查询所有专利")
    @GetMapping("/patent/all")
    public Response<List<WebAchievement>> getAllPatents() {
        return Response.ok(webAchievementService.lambdaQuery().eq(WebAchievement::getCategory, "专利").list());
    }

    @Operation(summary = "查询所有著作")
    @GetMapping("/book/all")
    public Response<List<WebAchievement>> getAllBooks() {
        return Response.ok(webAchievementService.lambdaQuery().eq(WebAchievement::getCategory, "著作").list());
    }

    @Operation(summary = "查询所有软著")
    @GetMapping("/writing/all")
    public Response<List<WebAchievement>> getAllWritings() {
        return Response.ok(webAchievementService.lambdaQuery().eq(WebAchievement::getCategory, "软著").list());
    }

    @Operation(summary = "按照技术标准的状态查询")
    @Parameter(name = "techniqueStatus", description = "技术标准的状态,包括申请中、已授权")
    @GetMapping("/tech/{techniqueStatus}")
    public Response<List<WebAchievement>> getTechsByStatus(@PathVariable("techniqueStatus") String techniqueStatus) {
        return Response.ok(webAchievementService.lambdaQuery().eq(WebAchievement::getCategory, "技术标准").eq(WebAchievement::getTechniqueStatus, techniqueStatus).list());
    }

    @Operation(summary = "查寻所有竞赛获奖")
    @GetMapping("/competition/all")
    public Response<List<WebAchievement>> getAllCompetitions() {
        return Response.ok(webAchievementService.lambdaQuery().eq(WebAchievement::getCategory, "竞赛").list());
    }
}

