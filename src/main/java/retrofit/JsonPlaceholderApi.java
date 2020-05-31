package retrofit;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface JsonPlaceholderApi {

    //GET
    @GET("{userUID}.json?auth=VBXgKwqQVmG9z3HWlBEz2XMISba4IPpHPbQ2f9g6")
    Call<JsonObject> getDatabase(@Path("userUID") String s);

    //POST
    @POST("relyingparty/signupNewUser?key=AIzaSyDyEXHGoJukMUw8SConzgcHZNWYDYsv11s")
    Call<JsonObject> signUp(@Body JsonObject j);

    @POST("relyingparty/verifyPassword?key=AIzaSyDyEXHGoJukMUw8SConzgcHZNWYDYsv11s")
    Call<JsonObject> signIn(@Body JsonObject j);

    @POST("./accounts:delete?key=AIzaSyDyEXHGoJukMUw8SConzgcHZNWYDYsv11s")
    Call<JsonObject> deleteAccount(@Body JsonObject j);

    @POST("./accounts:update?key=AIzaSyDyEXHGoJukMUw8SConzgcHZNWYDYsv11s")
    Call<JsonObject> changePassword(@Body JsonObject j);

    @POST("./accounts:update?key=AIzaSyDyEXHGoJukMUw8SConzgcHZNWYDYsv11s")
    Call<JsonObject> changeEmail(@Body JsonObject j);

    @POST("./accounts:sendOobCode?key=AIzaSyDyEXHGoJukMUw8SConzgcHZNWYDYsv11s")
    Call<JsonObject> resetPassword(@Body JsonObject j);

    //PUT
    @PUT("{userUID}/{uuid}/note.json?auth=VBXgKwqQVmG9z3HWlBEz2XMISba4IPpHPbQ2f9g6")
    Call<String> putNote(@Path("userUID") String userUID, @Path("uuid") String uuid, @Body String s);

    @PUT("{userUID}/{uuid}/latitude.json?auth=VBXgKwqQVmG9z3HWlBEz2XMISba4IPpHPbQ2f9g6")
    Call<Double> putLatitude(@Path("userUID") String userUID, @Path("uuid") String uuid, @Body Double d);

    @PUT("{userUID}/{uuid}/longitude.json?auth=VBXgKwqQVmG9z3HWlBEz2XMISba4IPpHPbQ2f9g6")
    Call<Double> putLongitude(@Path("userUID") String userUID, @Path("uuid") String uuid, @Body Double d);

    @PUT("{userUID}/{uuid}/title.json?auth=VBXgKwqQVmG9z3HWlBEz2XMISba4IPpHPbQ2f9g6")
    Call<String> putTitle(@Path("userUID") String userUID, @Path("uuid") String uuid, @Body String s);

    @PUT("{userUID}/{uuid}/date.json?auth=VBXgKwqQVmG9z3HWlBEz2XMISba4IPpHPbQ2f9g6")
    Call<Object> putDate(@Path("userUID") String userUID, @Path("uuid") String uuid, @Body Map<String, String> m);

    @PUT("{userUID}/{uuid}/uuid.json?auth=VBXgKwqQVmG9z3HWlBEz2XMISba4IPpHPbQ2f9g6")
    Call<String> putUUID(@Path("userUID") String userUID, @Path("uuid") String uuid, @Body String s);

    //DELETE
    @DELETE("{userUID}/{uuid}.json?auth=VBXgKwqQVmG9z3HWlBEz2XMISba4IPpHPbQ2f9g6")
    Call<Void> deleteNote(@Path("userUID") String userUID, @Path("uuid") String uuid);

    @DELETE("{userUID}.json?auth=VBXgKwqQVmG9z3HWlBEz2XMISba4IPpHPbQ2f9g6")
    Call<Void> deleteDatabase(@Path("userUID") String userUID);

}
