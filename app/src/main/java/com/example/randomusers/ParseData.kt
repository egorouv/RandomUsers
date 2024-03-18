import android.util.Log
import com.example.randomusers.User
import org.json.JSONObject

class ParseData {

    interface DataCallback {
        fun onDataParsed(users: List<User>)
        fun onDataParseFailed(error: String)
    }

    private val apiHandler = ApiHandler()
    private val TAG = "ParseData"

    fun parseData(callback: DataCallback) {

        apiHandler.getResponse(object : ApiHandler.ApiCallback {

            override fun onResponse(result: String) {

                val users = mutableListOf<User>()
                try {
                    val jsonObject = JSONObject(result)
                    val resultsArray = jsonObject.getJSONArray("results")
                    for (i in 0 until resultsArray.length()) {
                        val userObject = resultsArray.getJSONObject(i)

                        val nameObject = userObject.getJSONObject("name")
                        val name = "${nameObject.getString("title")} ${nameObject.getString("first")} ${nameObject.getString("last")}"

                        val addressObject = userObject.getJSONObject("location").getJSONObject("street")
                        val address = "${addressObject.getInt("number")} ${addressObject.getString("name")}, " +
                                "${userObject.getJSONObject("location").getString("city")}, " +
                                "${userObject.getJSONObject("location").getString("state")}, " +
                                "${userObject.getJSONObject("location").getString("country")}, " +
                                "${userObject.getJSONObject("location").getString("postcode")}"

                        val phone = userObject.getString("phone")

                        val pictureObject = userObject.getJSONObject("picture")
                        val image = pictureObject.getString("large")

                        val gender = userObject.getString("gender")

                        val email = userObject.getString("email")

                        val usernameObject = userObject.getJSONObject("login")
                        val username = usernameObject.getString("username")

                        val birthdayObject = userObject.getJSONObject("dob")
                        val birthday = birthdayObject.getString("date")

                        users.add(User(image, name, address, phone, gender, email, username, birthday))
                        //users.add(User(name, address, phone))
                        //Log.i(TAG, "$name $address $phone")
                    }
                    callback.onDataParsed(users)
                } catch (e: Exception) {
                    callback.onDataParseFailed(e.message ?: "Unknown error")
                }

            }

            override fun onFailure(error: String) {
                callback.onDataParseFailed(error)
            }
        })
    }
}
