import com.example.randomusers.User
import org.json.JSONObject

class ParseData {

    interface DataCallback {
        fun onDataParsed(users: List<User>)
        fun onDataParseFailed(error: String)
    }

    private val apiHandler = ApiHandler()

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
                        val name = "${nameObject.getString("first")} ${nameObject.getString("last")}"

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

                        val ageObject = userObject.getJSONObject("dob")
                        val age = ageObject.getString("age")

                        users.add(User(image, name, address, phone, gender, email, username, age))

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
