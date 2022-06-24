package pelaporan.kebakaran.logindandaftar.Api;

import java.util.Map;

import pelaporan.kebakaran.logindandaftar.DataModel.RegisterModel;
import pelaporan.kebakaran.logindandaftar.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterApi {

//    @FormUrlEncoded
    @POST("new_account.php")
    Call<Response> sendUser(@Body Map<String, String> requestBody);

    @POST("login.php")
    Call<Response> validateLogin(@Body Map<String, String> requestBody);
}
