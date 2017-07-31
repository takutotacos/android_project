package ramstalk.co.jp.project.domain.repository.factory;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by sugitatakuto on 2017/07/29.
 */

public class RetrofitAdapter {
    static volatile Retrofit retrofit = null;

    private RetrofitAdapter() {
    }

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                                   .baseUrl(baseUrl)
                                   .addConverterFactory(LoganSquareConverterFactory.create())
                                   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                   .build();
        }
        return retrofit;
    }

    private static final String baseUrl = "http://10.0.2.2:8000/project/";
//    private Retrofit retrofit;
////
//    public static Retrofit getRetrofit() {
//        if (retrofit != null) {
//            return retrofit;
//        }
//        createRetroFitAdapter();
//        return retrofit;
//    }
//    private static void createRetroFitAdapter() {
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//
//                //header設定
//                Request request = original.newBuilder()
//                        .header("Accept", "application/json")
//                        .method(original.method(), original.body())
//                        .build();
//
//                okhttp3.Response response = chain.proceed(request);
//
//                return response;
//            }
//        });
//        OkHttpClient client = httpClient.build();
//
//        retrofit = new Retrofit.Builder()
//                .client(client)
//                .baseUrl(baseUrl)
//                .addConverterFactory(LoganSquareConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//    }
//
//    public Retrofit getRetrofit

}
