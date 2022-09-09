package com.arneca.evyap.api.service;


import com.arneca.evyap.api.response.GetAllLineByKey;
import com.arneca.evyap.api.response.GetAllLineInfo;
import com.arneca.evyap.api.response.GetAllLineInfoByLine;
import com.arneca.evyap.api.response.GetFactories;
import com.arneca.evyap.api.response.GetKPIKeys;
import com.arneca.evyap.api.response.GetKVKConfirm;
import com.arneca.evyap.api.response.GetKVKK;
import com.arneca.evyap.api.response.GetLineInfo;
import com.arneca.evyap.api.response.GetLines;
import com.arneca.evyap.api.response.GetLogin;
import com.arneca.evyap.api.response.GetSendSecurtyCode;
import com.arneca.evyap.api.response.cmx.DocUpdateResponse;
import com.arneca.evyap.api.response.cmx.FooterInfoResponse;
import com.arneca.evyap.api.response.cmx.LoginResponse;
import com.arneca.evyap.api.response.cmx.NewDocResponse;
import com.arneca.evyap.api.response.cmx.OpenDocCompletedResponse;
import com.arneca.evyap.api.response.cmx.OpenDocRecordsResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentListResponse;
import com.arneca.evyap.api.response.cmx.OpenDocumentStockListResponse;
import com.arneca.evyap.api.response.cmx.PDFResponse;
import com.arneca.evyap.api.response.cmx.ProductSearchResponse;
import com.arneca.evyap.api.response.cmx.RBMatrisResponse;
import com.arneca.evyap.api.response.cmx.STHEkleRespone;
import com.arneca.evyap.api.response.cmx.TanimlarResponse;
import com.arneca.evyap.ui.activity.ChangePassword;

import java.util.HashMap;

import okhttp3.RequestBody;
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

    @POST("GetAllLineInfoByLine")
    Call<GetAllLineInfoByLine> GetAllLineInfoByLine(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("GetAllLineInfoByKey")
    Call<GetAllLineByKey> GetAllLineInfoByKey(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @POST("GetKPIKeys")
    Call<GetKPIKeys> GetKPIKeys(@HeaderMap HashMap<String, Object> headers, @Body HashMap<String, Object> map);

    @GET("Login")
    Call<GetLogin> getTokens(@HeaderMap HashMap<String, Object> headers);

    @GET("kvkk/getKvkkText")
    Call<GetKVKK> getKvkkText();

    @POST("kvkk/confirmKvkkByUserName")
    Call<GetKVKConfirm> confirmKvkkByUserName(@Body HashMap<String, Object> map);

    @POST("kvkk/confirmKvkkByMail")
    Call<GetKVKConfirm> confirmKvkkByMail(@Body HashMap<String, Object> map);

    @GET("password/resetPassword/{mail}")
    Call<GetSendSecurtyCode> sendSecurtyCode(@Path("mail") String mail);

    @POST("password/validateSecurityCode")
    Call<GetSendSecurtyCode> validateSecurtyCode(@Body HashMap<String, Object> map);

    @POST("password/changePasswordWithCode")
    Call<ChangePassword> changePassword(@Body HashMap<String, Object> map);

    /*
    *  Cemix
    *
    * */


    @POST("Login")
    Call<LoginResponse> login(@Body RequestBody body);

    @POST(" Login/SifreDegistir")
    Call<LoginResponse> changePass(@Body RequestBody body);

    @POST("Satis/AcikBelgeler")
    Call<OpenDocumentListResponse> openDocs(@Body RequestBody body);

    @POST("Satis/AcikBelgeStoklar")
    Call<OpenDocumentStockListResponse> openDocStocks(@Body RequestBody body);

    @POST("Satis/StokAra")
    Call<ProductSearchResponse> productSearch(@Body RequestBody body);

    @POST("Satis/RBMatris")
    Call<RBMatrisResponse> rbMAtris(@Body RequestBody body);

    @POST("Satis/STHEkle")
    Call<STHEkleRespone> sthEkle(@Body RequestBody body);

    @POST("Satis/AcikBelgeTamamla")
    Call<OpenDocCompletedResponse> openDocCompleted(@Body RequestBody body);

    @POST("Satis/YeniBelgeKontrol")
    Call<OpenDocRecordsResponse> openDocRecords(@Body RequestBody body);

    @POST("Satis/YeniBelge")
    Call<NewDocResponse> createNewDoc(@Body RequestBody body);

    @POST("Satis/BelgeDetaySil")
    Call<DocUpdateResponse> deleteDoc(@Body RequestBody body);

    @POST("Satis/BelgeSil")
    Call<DocUpdateResponse> deleteDocFromUplist(@Body RequestBody body);

    @POST("Satis/BelgeDetayMiktarGuncelle")
    Call<DocUpdateResponse> docUpdate(@Body RequestBody body);

    @POST("Sayim/TanimlariCek")
    Call<TanimlarResponse> getTanim(@Body RequestBody body);

    @POST("Satis/AcikBelgeAltToplam")
    Call<FooterInfoResponse> getFooterInfo(@Body RequestBody body);

    @POST("Satis/PDFRapor")
    Call<PDFResponse> getPDF(@Body RequestBody body);

    @POST("Satis/RaporYazdir")
    Call<PDFResponse> getPrint(@Body RequestBody body);


}
