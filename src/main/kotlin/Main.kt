import com.google.gson.GsonBuilder
import io.ktor.client.HttpClient
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import jdk.jfr.ContentType
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption


// Вспомогательные классы из Андроид проекта
open class Post (

    // Данные поста введенные автором
    val id: Long,
    val author: String,
    val content: String?,
    val created: Long,


    // Начальные данные поста
    val type: PostType = PostType.POST,
    val source: Post? = null,
    var likedByMe: Boolean = false,
    var CommentdByMe: Boolean = false,
    var ShareByMe: Boolean = false,

    // Счетчики интереса к посту
    var likedCounter: Int = 0,
    var commentCounter:Int = 0,
    var shareCounter: Int = 0,

    // Поля для поста Event
    val locationByMe: Boolean? = null,
    val address: String? = null,
    val coordinates_lat: Double? = 0.0,
    val coordinates_lng: Double? = 0.0,

    // Поля для поста VIDEO
    val videolink: String? = null,

    // Поля для поста ADVERTISING
    val advertisinglink: String? = null


)

enum class PostType {
    POST, REPOST, EVENT, ADVERTISING, VIDEO
}



fun main() {


    // Пост для имитации репоста
    var post = Post(
        0,
        "Джонатон Уэйн",
        "Самое первое сообщение в этой социальной сети. Где отображаются переносы слов, но не более ограниченных строк.",
        1550864590000,
        likedByMe = false,
        CommentdByMe = false,
        ShareByMe = false,
        likedCounter = 5,
        commentCounter = 3,
        shareCounter = 2
    )

    // База предустановленных постов
    val list = mutableListOf(
        Post(
            1,
            "Мистер Смит",
            "Матрица жива!!!",
            1564864590000,
            type = PostType.REPOST,
            source = post,
            likedByMe = true,
            CommentdByMe = true,
            likedCounter = 15,
            commentCounter = 1
        ),
        Post(
            2,
            "Нео Протуберанец",
            "Великое деяние Архитектора заключается в поиске багов в процессе всей жизни.",
            1574864590000,
            type = PostType.REPOST,
            source = post,
            likedByMe = true,
            ShareByMe = true,
            likedCounter = 1,
            shareCounter = 1
        ),
        post,

        Post(
            3,
            "Петр Авергунг",
            "Добро пожаловать в мир цифровой недвижимости!",
            1584864590000,
            likedByMe = true,
            CommentdByMe = true,
            ShareByMe = true,
            likedCounter = 4,
            commentCounter = 11,
            shareCounter = 1,
            type = PostType.ADVERTISING,
            advertisinglink = "https://habr.com/ru/"

        ),
        Post(
            4,
            "Аноним",
            "Всемирная теория заговора включает неизменную идею мягкого влияния на людей, не имеющих доступа к власти и ресурсам.",
            1594864590000,
            type = PostType.VIDEO,
            videolink = "https://www.youtube.com/watch?v=EOkIIyuGAUs"
        ),
        Post(
            5,
            "Дед Мороз",
            "Подготовка к Новому году включает в себя список дел который известен каждому.",
            1604864590000,
            likedByMe = true,
            CommentdByMe = true,
            ShareByMe = true,
            likedCounter = 150,
            commentCounter = 190,
            shareCounter = 70,
            type = PostType.EVENT,
            address = "Великий Устюг",
            coordinates_lat = 41.40338,
            coordinates_lng = 2.17403
        ),
        Post(
            6,
            "Дед Мазай",
            "Исследование костромских болот на личном примере.",
            1614864590000,
            type = PostType.VIDEO,
            videolink = "https://www.youtube.com/watch?v=EOkIIyuGAUs",
            address = "Нея",
            coordinates_lat = 41.40338,
            coordinates_lng = 2.17403
        )

    )


    // Выводим посты преобразованные в JSON
    // println(Gson().toJson(list))

    // ******************************
    // Код для подготовки базы в json
    // ******************************

    // Серелизуем информацию в json
    val gson = GsonBuilder().apply {
        setPrettyPrinting()
        serializeNulls()
    }.create()

    // Создаем файл json в текущем каталоге
    Files.write(Paths.get("./posts.json"), gson.toJson(list).toByteArray(), StandardOpenOption.CREATE)


    // ****************************
    // Вызов массива json с сервера
    // ****************************

    /*val url_git_hub = "https://raw.githubusercontent.com/zlayada/KotlinHomeWork6_1/master/app/src/main/java/com/netology/kotlinhomework6_1/JsonDate/posts.json"

        val client = HttpClient {
            install(JsonFeature) {

                // объясним чуть позже
                acceptContentTypes = listOf(
                    ContentType.Text.Plain,
                    ContentType.Application.Json
                )
                serializer = GsonSerializer()
            }
        }

        // тестовый ответ будет десериализован в List<Post>

        val response = client.get<List<Post>>(url_git_hub)
        println(response)
        client.close()
*/


}







