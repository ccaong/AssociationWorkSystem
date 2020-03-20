package com.example.gqsystem.http.request;

import com.example.gqsystem.bean.response.CompanyBasisInfoBean;
import com.example.gqsystem.bean.response.CompanyContactBean;
import com.example.gqsystem.bean.response.CompanyInstudry;
import com.example.gqsystem.bean.response.CompanyListBean;
import com.example.gqsystem.bean.response.InvoiceInfoBean;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.bean.response.PersonListBean;
import com.example.gqsystem.bean.response.ReviewerListBean;
import com.example.gqsystem.bean.response.UserDataBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 网络请求接口地址
 *
 * @author devel
 */
public interface ApiAddress {


    @Multipart
    @POST("hd/uploadFile")
    Observable<Object> upload(@Part MultipartBody.Part file, @Part("type") RequestBody type);

    @GET("HPImageArchive.aspx")
    Observable<Object> getImage(@Query("format") String format, @Query("idx") int idx, @Query("n") int n);


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
    Observable<List<InvoiceInfoBean>> getCompanyInvoice(@Query("id") Integer id);


    /**
     * 企业基本信息查询
     *
     * @param id
     * @return
     */
    @GET("companyinformation/companyInformation/queryById")
    Observable<CompanyBasisInfoBean> getCompanyConstant(@Query("id") Integer id);

    /**
     * 企业所属行业查询
     *
     * @param id
     * @return
     */
    @GET("companyinformation/companyInformation/queryComIndustryByMainId2")
    Observable<List<CompanyInstudry>> queryComIndustryByMainId(@Query("id") Integer id);


    /**
     * 企业联系人集合
     *
     * @param id
     * @return
     */
    @GET("companyinformation/companyInformation/queryCompanyUserByMainId")
    Observable<List<CompanyContactBean>> queryCompanyUserByMainId(@Query("id") Integer id);


}
