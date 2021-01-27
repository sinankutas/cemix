package com.arneca.evyap.api.service;


import com.arneca.evyap.api.response.GetAllLineInfo;
import com.arneca.evyap.api.response.GetFactories;
import com.arneca.evyap.api.response.GetLineInfo;
import com.arneca.evyap.api.response.GetLines;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Services {

    @POST("GetFactories")
    Call<GetFactories> GetFactories(@Body HashMap<String, Object> map);

    @POST("GetLines")
    Call<GetLines> GetLines(@Body HashMap<String, Object> map);

    @POST("GetLineInfo")
    Call<GetLineInfo> GetLineInfo(@Body HashMap<String, Object> map);

    @POST("GetAllLineInfo")
    Call<GetAllLineInfo> GetAllLineInfo(@Body HashMap<String, Object> map);



  /*  @POST("/Thingworx/Things/SMPRK_MobileApp_API_Thing/Services/GetFloorData")
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
