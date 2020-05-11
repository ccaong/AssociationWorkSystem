package com.example.gqsystem.http.request;

import com.example.gqsystem.bean.response.CompanyPerInfoBean;
import com.example.gqsystem.bean.response.ProjectAssessInfoBean;
import com.example.gqsystem.bean.response.ProjectConsultInfo;
import com.example.gqsystem.bean.response.ProjectConsultOtherInfo;
import com.example.gqsystem.bean.response.CompanyBasisInfoBean;
import com.example.gqsystem.bean.response.CompanyContactBean;
import com.example.gqsystem.bean.response.CompanyInstudry;
import com.example.gqsystem.bean.response.CompanyListBean;
import com.example.gqsystem.bean.response.CompanyRelatedBean;
import com.example.gqsystem.bean.response.DataShareListBean;
import com.example.gqsystem.bean.response.DicItemBean;
import com.example.gqsystem.bean.response.InvoiceInfoBean;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.bean.response.MeetingListBean;
import com.example.gqsystem.bean.response.PersonListBean;
import com.example.gqsystem.bean.response.ProjectComMaterialBean;
import com.example.gqsystem.bean.response.ProjectContractInfoBean;
import com.example.gqsystem.bean.response.ProjectContractPaymentBean;
import com.example.gqsystem.bean.response.ProjectCostAccountBean;
import com.example.gqsystem.bean.response.ProjectGovInfo;
import com.example.gqsystem.bean.response.ProjectJudgeInfoBean;
import com.example.gqsystem.bean.response.ProjectListBean;
import com.example.gqsystem.bean.response.ReviewerListBean;
import com.example.gqsystem.bean.response.UserDataBean;
import com.example.gqsystem.bean.response.UserEmailBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 网络请求接口地址
 *
 * @author devel
 */
public interface ApiAddress {


    /**
     * 获取系统字典信息
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("sys/dictItem/list")
    Observable<DicItemBean> getDictItem(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize);


    /**
     * 文件上传
     *
     * @param file
     * @param type
     * @return
     */
    @Multipart
    @POST("hd/uploadFile")
    Observable<Object> upload(@Part MultipartBody.Part file, @Part("type") RequestBody type);

    /**
     * 文件下载
     *
     * @param url
     * @return
     */
    @Streaming
    @GET()
    Call<ResponseBody> downloadFileWithFileName(@Url String url);


    /**
     * 登录
     *
     * @param map
     * @return
     */
    @POST("sys/mLogin")
    Observable<UserDataBean> login(@Body Map<String, Object> map);


    /**
     * 忘记密码第一步
     * 获取用户邮箱
     *
     * @param userName
     * @return 邮箱信息
     */
    @GET("sys/user/querySysUser2")
    Observable<UserEmailBean> getEmailByUserName(@Query("username") String userName);

    /**
     * 忘记密码第二步
     * 发送验证码
     *
     * @param map
     * @return
     */
    @POST("sys/email2")
    Observable<Response<Void>> sendVerificationCode(@Body Map<String, Object> map);

    /**
     * 忘记密码第三步
     * 校验验证码
     *
     * @param map
     * @return
     */
    @POST("sys/user/emailVerification")
    Observable<Response<Void>> emailVerification(@Body Map<String, Object> map);


    /**
     * 忘记密码第四步
     * 重置密码
     *
     * @param userName
     * @return
     */
    @GET("sys/user/passwordChange")
    Observable<Response<Void>> changePwd(@Query("username") String userName, @Query("email") String email, @Query("smscode") String smscode, @Query("password") String password);


    /**
     * 退出
     *
     * @return
     */
    @POST("sys/logout")
    Observable<Response<Void>> logout();

    /**
     * 公司人员列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("sys/user/list")
    Observable<PersonListBean> getPersonList(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize);


    /**
     * 获取评审人员列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("reviewer/basReviewer/list")
    Observable<ReviewerListBean> getReviewerList(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize);

    /**
     * 领导动态
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("leaderactivity/leaderActivity/list")
    Observable<LeaderActivityListBean> getLeaderActivity(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize);

    /**
     * 领导动态 - 添加
     *
     * @param map
     * @return
     */
    @POST("leaderactivity/leaderActivity/add")
    Observable<Response<Void>> addLeaderActivity(@Body Map<String, Object> map);

    /**
     * 领导动态 - 删除
     *
     * @param id
     * @return
     */
    @HTTP(method = "DELETE", path = "leaderactivity/leaderActivity/delete", hasBody = true)
    Observable<Response<Void>> deleteLeaderActivity(@Query("id") String id);


    /**
     * 领导动态 - 编辑
     *
     * @param map
     * @return
     */
    @PUT("leaderactivity/leaderActivity/edit")
    Observable<Response<Void>> deleteLeaderActivity(@Body Map<String, Object> map);


    /**
     * 企业列表查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("companyinformation/companyInformation/list")
    Observable<CompanyListBean> getCompanyList(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize, @Query("comName") String comName);


    /**
     * 企业开票信息查询
     *
     * @param id
     * @return
     */
    @GET("companyinformation/companyInformation/queryCompanyInvoiceByMainId")
    Observable<List<InvoiceInfoBean>> getCompanyInvoice(@Query("id") String id);


    /**
     * 企业基本信息查询
     *
     * @param id
     * @return
     */
    @GET("companyinformation/companyInformation/queryById")
    Observable<CompanyBasisInfoBean> getCompanyConstant(@Query("id") String id);

    /**
     * 企业所属行业查询
     *
     * @param id
     * @return
     */
    @GET("companyinformation/companyInformation/queryComIndustryByMainId2")
    Observable<List<CompanyInstudry>> queryComIndustryByMainId(@Query("id") String id);


    /**
     * 企业联系人集合
     *
     * @param id
     * @return
     */
    @GET("companyinformation/companyInformation/queryCompanyUserByMainId")
    Observable<List<CompanyContactBean>> queryCompanyUserByMainId(@Query("id") String id);

    /**
     * 关联企业
     *
     * @param id
     * @return
     */
    @GET("companyinformation/companyInformation/queryRelationCompanyByMainId")
    Observable<List<CompanyRelatedBean>> queryRelationCompanyByMainId(@Query("id") String id);

    /**
     * 企业资料
     *
     * @param id
     * @return
     */
    @GET("projectinformation/proInformation/queryProComMaterialByMainId")
    Observable<List<Object>> queryCompanyMaterial(@Query("id") String id);


    /**
     * 会议列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("meetmangement/meetingMangement/list")
    Observable<MeetingListBean> queryMeetingList(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize);


    /**
     * 文件分享
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("datashare/basDataShare/list")
    Observable<DataShareListBean> queryDataShareList(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize, @Query("dataFile") String dataFile);


    /**
     * 项目列表 查看所有
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("projectinformation/proInformation/list")
    Observable<ProjectListBean> projectInfoList(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize);

    /**
     * 项目列表 有权限
     *
     * @param pageNo
     * @param pageSize
     * @param proType
     * @return
     */
    @GET("projectinformation/proInformation/listPermission")
    Observable<ProjectListBean> projectInfoListPermission(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize,
                                                          @Query("proType") String proType, @Query("proStatus") String proStatus, @Query("comId") String comId);


    /**
     * 项目列表 有权限
     *
     * @param pageNo
     * @param pageSize
     * @param comName    企业名称
     * @param projectPer 项目负责人
     * @param marketPer  市场负责人
     * @return
     */
    @GET("projectinformation/proInformation/listPermission")
    Observable<ProjectListBean> searchProjectInfoList(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize,
                                                      @Query("comName") String comName, @Query("marketPer") String marketPer, @Query("projectPer") String projectPer);


    /**
     * 企业对接联系人信息
     *
     * @param id
     * @return
     */
    @GET("companyinformation/companyInformation/queryCompanyUserById")
    Observable<CompanyPerInfoBean> queryCompanyUser(@Query("id") String id);


    /**
     * 合同信息
     *
     * @param id
     * @return
     */
    @GET("projectinformation/proInformation/queryProContractInfoByMainId")
    Observable<List<ProjectContractInfoBean>> queryProContractInfo(@Query("id") String id);


    /**
     * 合同款项
     *
     * @param id
     * @return
     */
    @GET("projectinformation/proInformation/queryProContractPaymentByMainId")
    Observable<List<ProjectContractPaymentBean>> queryProContractPayment(@Query("id") String id);

    /**
     * 成本核算
     *
     * @param id
     * @return
     */
    @GET("projectinformation/proInformation/queryProCostAccountingByMainId")
    Observable<List<ProjectCostAccountBean>> queryProCostAccounting(@Query("id") String id);


    /**
     * 企业资料
     *
     * @param id
     * @return
     */
    @GET("projectinformation/proInformation/queryProComMaterialByMainId")
    Observable<List<ProjectComMaterialBean>> queryProComMaterial(@Query("id") String id);


    /**
     * 项目详细信息（政府项目）
     *
     * @param id
     * @return
     */
    @GET("projectinformation/proInformation/queryProGovernmentByMainId")
    Observable<List<ProjectGovInfo>> queryProGovernment(@Query("id") String id);


    /**
     * 项目详细信息（咨询类-现场）
     *
     * @param id
     * @return
     */
    @GET("projectinformation/proInformation/queryProConsultingByMainId")
    Observable<List<ProjectConsultInfo>> queryProConsulting(@Query("id") String id);


    /**
     * 项目详细信息（咨询类-其他）
     *
     * @param id
     * @return
     */
    @GET("projectinformation/proInformation/queryProConsultingOtherByMainId")
    Observable<List<ProjectConsultOtherInfo>> queryProConsultingOther(@Query("id") String id);

    /**
     * 评价评估及应急预案集合
     *
     * @param id
     * @return
     */
    @GET("projectinformation/proInformation/queryProAssessByMainId")
    Observable<List<ProjectAssessInfoBean>> queryProAssess(@Query("id") String id);

    /**
     * 评审类项目集合
     *
     * @param id
     * @return
     */
    @GET("projectinformation/proInformation/queryProJudgeByMainId")
    Observable<List<ProjectJudgeInfoBean>> queryProJudge(@Query("id") String id);

}
