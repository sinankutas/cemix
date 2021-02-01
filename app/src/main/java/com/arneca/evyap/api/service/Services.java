package com.arneca.evyap.api.service;


import com.arneca.evyap.api.response.GetAllLineInfo;
import com.arneca.evyap.api.response.GetFactories;
import com.arneca.evyap.api.response.GetLineInfo;
import com.arneca.evyap.api.response.GetLines;
import com.arneca.evyap.api.response.GetLogin;
import com.arneca.evyap.api.response.GetSendSecurtyCode;
import com.arneca.evyap.ui.activity.ChangePassword;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Services {

    @POST("GetFactories")
    Call<GetFactories> GetFactories(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("GetLines")
    Call<GetLines> GetLines(@HeaderMap HashMap<String, Object> headers,@Body HashMap<String, Object> map);

    @POST("GetLineInfo")
    Call<GetLineInfo> GetLineInfo(@HeaderMap HashMap<String, Object> headers,@Body HashMap<String, Object> map);

    @POST("GetAllLineInfo")
    Call<GetAllLineInfo> GetAllLineInfo(@HeaderMap HashMap<String, Object> headers,@Body HashMap<String, Object> map);

    @GET("Login")
    Call<GetLogin> getTokens(@HeaderMap HashMap<String, Object> headers);

    @GET("password/resetPassword/{mail}")
    Call<GetSendSecurtyCode> sendSecurtyCode(@Path("mail") String mail);

    @POST("password/validateSecurityCode")
    Call<GetSendSecurtyCode> validateSecurtyCode(@Body HashMap<String, Object> map);

    @POST("password/changePasswordWithCode")
    Call<ChangePassword> changePassword(@Body HashMap<String, Object> map);



  /*
    @POST("Users/{userName}/Services/GetApplicationKeyExpirationInfo")
    Call<ApplicationKeyExpirationInfo> getTokens(@HeaderMap HashMap<String, Object> headers, @Path("userName") String userName, @Body HashMap<String, Object> map);


@POST("/Thingworx/Things/SMPRK_MobileApp_API_Thing/Services/GetFloorData")
    Call<FloorData> getFloorData(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("/Thingworx/Things/SMPRK_MobileApp_API_Thing/Services/GetParkingSlotCountByFloorNameAndLocation")
    Call<ParkingSlotCountByFloorNameAndLocation> getParkingSlotCountByFloorNameAndLocation(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("/Thingworx/Things/SMPRK_MobileApp_API_Thing/Services/GetParkingSummaryByFloorName")
    Call<ParkingSummaryByFloorName> getParkingSummaryByFloorName(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("/Thingworx/Things/SMPRK_MobileApp_API_Thing/Services/GetMemberInfo")
    Call<MemberInfoResponse> getMemberInfo(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("/Thingworx/Things/SMPRK_MobileApp_API_Thing/Services/ChangeMemberPassword")
    Call<ChangePasswordResponse> changePass(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("/Thingworx/Things/SMPRK_MobileApp_API_Thing/Services/GetMemberStats")
    Call<MemberStatsResponse> getMemberStats(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("/Thingworx/Things/SMPRK_MobileApp_API_Thing/Services/GetReservedParkingSlotForMember")
    Call<ReservervedParkingResponse> getReservedInfo(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("/Thingworx/Things/SMPRK_MobileApp_API_Thing/Services/UpdateReleaseCalendar")
    Call<ReleaseListResponse> getReleaseList(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("/Thingworx/Things/SMPRK_MobileAppSecurity_API_Thing/Services/SendSecurityCodeViaEmail")
    Call<SendSecurityCodeViaEmailResponse> getSecurtyCode(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("/Thingworx/Things/SMPRK_MobileAppSecurity_API_Thing/Services/AssignNewPasswordWithSecurityCode")
    Call<AssignNewPasswordWithSecurityCodeResponse> assignNewPass(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);
*/
}
