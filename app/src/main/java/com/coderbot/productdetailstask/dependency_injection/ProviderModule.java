package com.coderbot.productdetailstask.dependency_injection;

import com.coderbot.productdetailstask.data.repository.ProductRepositoryImplementation;
import com.coderbot.productdetailstask.domain.usecase.GetProductDetails;
import com.coderbot.productdetailstask.domain.usecase.OrderProduct;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.coderbot.productdetailstask.domain.utils.Constants.URL;

@Module
class ProviderModule
{
	@Singleton
	@Provides
	Retrofit provideRetrofit()
	{
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
		return new Retrofit.Builder().baseUrl(URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
	}

	@Singleton
	@Provides
	ProductRepositoryImplementation provideProductRepository(Retrofit retrofit)
	{
		return new ProductRepositoryImplementation(retrofit);
	}

	@Singleton
	@Provides
	GetProductDetails provideGetProductDetails(ProductRepositoryImplementation repository)
	{
		return new GetProductDetails(repository);
	}

	@Singleton
	@Provides
	OrderProduct provideOrderProduct(ProductRepositoryImplementation repository)
	{
		return new OrderProduct(repository);
	}
}
