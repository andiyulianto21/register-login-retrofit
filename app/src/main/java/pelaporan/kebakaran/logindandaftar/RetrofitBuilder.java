package pelaporan.kebakaran.logindandaftar;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static Retrofit INSTANCE;

    public static synchronized Retrofit getRetrofit(){
        if(INSTANCE == null){
            INSTANCE = new Retrofit.Builder().baseUrl("http://10.0.2.2/registerlogin/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }

}
