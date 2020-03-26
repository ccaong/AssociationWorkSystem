package com.example.gqsystem.http.request;

import com.example.gqsystem.bean.response.CompanyBasisInfoBean;
import com.example.gqsystem.bean.response.CompanyContactBean;
import com.example.gqsystem.bean.response.CompanyInstudry;
import com.example.gqsystem.bean.response.CompanyListBean;
import com.example.gqsystem.bean.response.CompanyRelatedBean;
import com.example.gqsystem.bean.response.InvoiceInfoBean;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.bean.response.PersonListBean;
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
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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
     * @param fileName
     * @return
     */
    @Streaming
    @GET("download/downloadFile/temp/{fileName}")
    Call<ResponseBody> downloadFileWithFileName(@Path("fileName") String fileName);


    /**
     * @param page
     * @param type
     * @param priority
     * @return
     */
    @POST("lg/todo/v2/list/{page}/json")
    Observable<Object> getToDoList(@Path("page") int page,
                                   @Query("type") int type, @Query("priority") int priority);


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
     * 企业列表查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("companyinformation/companyInformation/list")
    Observable<CompanyListBean> getCompanyList(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize);


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
     * 会议列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("meetmangement/meetingMangement/list")
    Observable<Object> queryMeetingList(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize);


}
