package com.example.vegetableplantingtutorial;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Vegetable.db";

    public static final String TABLE_NAME = "Vegetable_table";
    public static final String COL_1 = "ID";                            //  0
    public static final String COL_2 = "NAME";                          //  1
    public static final String COL_3 = "DESCRIPTION";                   //  2
    public static final String COLUMN_VEGETABLE_URL = "VEGETABLE_URL";  //  3
    public static final String COLUMN_CATEGORY_FK = "category_id";      //  4
    public static final String COLUMN_VEGETABLE_IMAGE = "image";        //  5


    public static final String CATEGORY_TABLE = "categories";
    public static final String CATEGORY_ID = "id";
    public static final String CATEGOR_NAME = "name";


    public static final String ALTER_VEGETABLE_TABLE_1 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " +
            "" + COLUMN_VEGETABLE_URL + " TEXT";
    public static final String ALTER_VEGETABLE_TABLE_2 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " +
            "" + COLUMN_CATEGORY_FK + " INTEGER";
    public static final String ADD_IMAGE_COLUMN = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_VEGETABLE_IMAGE + " BLOB";

    public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS " +
            "" + CATEGORY_TABLE + " " +
            "("+ CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ""+ CATEGOR_NAME +" TEXT)";


    public static final int SCHEMA_VERSION = 4;

    Context cont;

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] byteArray;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        cont = context;
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + COL_2 + " TEXT, " +
                "" + COL_3 + " TEXT)");

        db.execSQL("INSERT INTO "+TABLE_NAME+" (NAME,DESCRIPTION) VALUES " +
                "('Eggplant','Eggplant, Solanum melongena, is a tropical, herbaceous, perennial plant, closely related to tomato, in the family Solanaceae which is grown for its edible fruit. The plants has a branching stem and simple, long, flat. coarsely lobed leaves which are green in color and are arranged alternately on the branches. The leaves can measure 10 to 20 cm (4–8 in) long and 5 to 10 cm (2–4 in) broad. The plant produces purple flowers which are 3–5 cm (1.2–2.0 in) in diameter. The fruit is a large, fleshy ovoid berry which can reach 40 cm (15.7 in) in length, with glossy smooth skin and numerous small seeds. The color of the fruit is variable and can be white, green, yellow, purple or black. Eggplants can reach up to 1.5 m (4.9 ft) in height and although they are perennial plants, they are most commonly grown as annuals. Eggplant may also be referred to as aubergine or guinea squash and originates from the Indian subcontinent.\n" +
                "\n" +
                "PROPAGATION:\n" +
                "Basic requirements Eggplants are warm-season crops which require a long growing season.They grow best in regions where the daytime temperature is between 26 and 32°C (80–90°F) and night time temperatures around 21°C (70°F). In addition, eggplant is a sun loving plant and should be positioned in an area that receives full sunlight. Plants will perform best when planted in a fertile soil with a pH between 6.3 and 6.8. Sowing seeds In cold areas and regions with a short growing season it is necessary to start eggplant indoors or in a glasshouse. In addition, eggplant will often perform much better in colder areas if planted in containers or grown under row covers as this helps to keep the soil warm. Seeds should be sown indoors 6 to 8 weeks before the last frost date. Sow seeds thickly in seed trays containing good quality sterile seed starting mix to a depth of 6 mm (0.25 in). Keep the trays moist and provide bottom heat by placing on a heat mat or in a warm area of the house. Seedling should be potted into larger pots when they have two sets of true leaves. Transplanting Eggplant seedlings can be transplanted after hardening-off and when all danger of frost has passed in your area. Seedlings should be spaced 45–60 cm (18–24 in) apart, depending on variety, allowing a further 60–90 cm (24–36 in) between rows. General care and maintenance Eggplant may benefit from the addition of mulch to conserve soil moisture and maintain a higher soil temperature. Row covers will help to increase the temperature around the plants in cooler climates and during cool spells in otherwise warm areas. The row covers should be removed to allow pollinators to access the plants during flowering. Eggplant should be provided with a steady water supply for optimum development of fruit and the soil around the plants should not be allowed to dry out but should also not be wet. Plants can be laden with numerous fruit and the use of stakes and supports can help to support the plants before harvest. Harvesting Eggplant fruits are ready to harvest while the flesh is still firm and seeds are small and tightly packed. The skin of the fruit should be firm, glossy and dark purple. Over ripe fruit will contain darker seeds and will taste bitter. Harvest the fruit as soon as it is ripe to ensure maximum productivity. The fruit should be removed from the plant by cutting the calyx (green stem above the fruit) with a sharp knife.')," +
                "('Tomato','Description:\n" +
                "Tomato, Lycopersicum esculentum (syn. Solanum lycopersicum and Lycopersicon lycopersicum) is an herbaceous annual in the family Solanaceae grown for its edible fruit. The plant can be erect with short stems or vine-like with long, spreading stems. The stems are covered in coarse hairs and the leaves are arranged spirally. The tomato plant produces yellow flowers, which can develop into a cyme of 3–12, and usually a round fruit (berry) which is fleshy, smoothed skinned and can be red, pink, purple, brown, orange or yellow in color. The tomato plant can grow 0.7–2 m (2.3–6.6 ft) in height and as an annual, is harvested after only one growing season. Tomato may also be referred to as love apple and originates from South America.\n" +
                "\n" +
                "PROPAGATION:\n" +
                "Requirements Tomatoes grow very well in warm areas at temperatures between 21 and 24°C (69.8–75.2°F). They require a deep, loamy, well-draining soil with a pH between 5.5 and 6.8. If soil drainage is a problem then tomatoes can be planted in a raised bed. Like all fruiting plants, tomatoes require full sun for most of the day. Tomato varieties Determinate (“bush”) tomatoes have been purposefully bred to grow vertically and remain relatively compact. The plant will stop growing once fruit begins developing on the terminal shoot and all the fruits ripen at around the same time. In contrast, indeterminate (“vining”) tomato varieties spread laterally and will continue to grow and produce fruit until frosts begin the the Fall. Indeterminate varieties can produce fruit all season and fruits will develop and ripen at different times. Heirloom tomatoes are generally open-pollinated varieties which have been conserved over many generations due to certain desirable characteristics such as flavor. Hybrid tomatoes are the product of cross-pollination between two parents with desirable characteristics such as high yield, early maturation, improved flavor or resistance to certain diseases. Sowing seeds In most cases, tomato seeds should be started indoors 6–8 weeks before last Spring frost. Seeds can be direct seeded in areas with a long growing season. Seeds should be sown in flats or cell trays using a sterile seedling mix. Plant seeds to a depth of 0.6 cm (1/4 in) and water lightly. If cells are being used, plant several seeds in each cell and thin to 1 seedling after germination. Position trays in a bright South facing window or under fluorescent lighting. The optimum soil temperature for germination is 21–32°C (70–90°F). A heat mat can be used to warm the flats if required. Seedlings should emerge within 6-14 days and after the seedlings has developed the first set of true leaves then they can be moved to a larger (3-4 in) pot and moved to a cooler temperature (16–21°C/60–70°F). Transplanting Tomato seedlings are ready to be transplanted once they are 15–25 cm (6–10 in) in height and have 3–5 true leaves assuming all danger of frost has passed. Beginning approximately mo 7-10 days before transplanting, plants should be set outside to harden off (see https://www.plantvillage.com/posts/264). The transplanting site should be prepared by working in a balanced fertilizer according to the guidelines on the product label. Transplants should be spaced 76–123 cm (30–48 in) apart with a between row spacing of 123 cm (48 in). It is common practice to plant tomatoes in trenches on their side (see https://www.plantvillage.com/posts/833-tomato) as tomato stems will sprout roots along their length when buried. Avoid over-fertilizing transplants, particularly with nitrogen, at this stage of growth as it will promote growth of foliage rather than fruits. Water plants lightly at base instead of overhead as wet foliage is more prone to diseases and the buried stem needs time to adapt and sprout roots. It is important that tomato plants receive even watering to prevent the development of blossom end rot, drip or soaker hoses work best and mulching around the plants helps to conserve soil moisture. Stakes, Cages and Trellises Staking, caging or trellising tomatoes supports the plants and helps to keep fruit off of the ground as well as increasing air circulation around the foliage which helps to prevent disease. The type of support system used depends on the type of tomatoes being grown. Determinate tomatoes have short or medium length vines and stop growing once fruit develops on the terminal branches. Determinates can be staked or caged but do not adapt to trellises. The position of the fruit means that little heavy pruning is required. In contrast, indeterminate tomatoes grow indefinitely and require a support system to prevent them trailing along the ground. The amount of pruning required depends on the support system being utilized - vines require only light pruning when caged, moderate pruning when staked and heavy pruning when using a trellis.')," +
                "('Cabbage','Description\n" +
                "The cabbage plant, Brassica oleracea, is an herbaceous annual or biennial vegetable in the family Brassicaceae grown for its edible head. There are many different varieties of cabbage which include the white and red cabbage (Brassica oleracea var. capitata) and the savoy cabbage (Brassica oleracea var. sabauda. The head of the cabbage is round and forms on a short thick stem. The leaves are thick and alternating with wavy or lobed edges and the roots are are fibrous and shallow. The plant produces large yellow flowers. The densely leaved heads can range in size from 0.5 to 3.6 kg (1-8 lb) depending on variety. The plant is usually grown as an annual. Brassica oleracea may be referred to as cabbage, Shetland cabbage, Savoy cabbage, white cabbage or red cabbage and is believed to have originated from a wild cabbage ancestor in ancient Asia minor.\n" +
                "\n" +
                "Uses\n" +
                "Cabbage is primarily grown for consumption as a vegetable, eaten after boiling or steaming. Some cultivars are grown as fodder for animals. Red cabbage is commonly pickled.\n" +
                "\n" +
                "Propagation\n" +
                "Basic requirements Cabbage is cool season crop that grows best in cool, moist conditions. The plant will grow best at temperatures between 4 and 21°C (40–50°F) allowing it to be grown in both Spring and Fall. Cabbage will grow optimally in a rich, moist, well draining soil With a pH of 6.5. The plant requires at least six hours of direct sunlight every day. Sowing seeds Cabbage can be direct seeded or started indoors to produce transplants. The optimum soil temperature for germination is between 12 and 24°C (55–75°F). Cabbage seeds can be planted outdoors 6–8 weeks before the last spring frost date in a cold frame and transplanted to their final location approximately 4 weeks before the last frost. If planting for a fall harvest, cabbage can be direct seeded 6–8 weeks before the first frost date. Prepare the soil for planting through the addition of nitrogen in the form of bone meal or composted manure. Plant cabbage seeds 6 mm (0.25 in) deep allowing 10–15 cm (4–6 in) between plants in the row and a 0.6 to 1.2 m (2–4 ft) between rows. Thin seedlings to a final within row spacing of 45–60 cm (18–24 in). Keep soil moist during germination to prevent a crust from forming on the soil surface as this will cause uneven germination. Transplanting Seedlings started indoors or in a cold frame are ready to be transplanted when they have 3–4 leaves and the daytime temperature has reached 10°C (50°F). Seedling should be planted at the final spacing for seeds (45–60 cm/18–24 in between plants and 0.6 to 1.2 m/2–4 ft between rows). Plant each seedling slightly deeper than it was previously. The plantings can be staggered in 2 week intervals to prolong the harvest. General care and maintenance Cabbage should be kept evenly watered to ensure the development of tight heads. Uneven watering can cause heads to crack. Application of mulch around plants helps to conserve soil moisture. Cabbage plants have shallow roots and in order to avoid damaging them, it is preferable to hand pull any weeds growing around the plants. Cabbages are heavy feeders and require the addition of fertilizer to meet their growth requirements and develop optimally. Fertilize the plants when they are beginning to form new leaves and starting to develop heads. Harvesting Cabbages are ready to harvest when the head is fully formed and feels firm and well-packed when squeezed. Cut the head away from the stalk with a sharp knife. Leaving the stalks in the ground will result in the formation of several smaller heads which can also be harvested and eaten.')," +
                "('String Beans','Sitaw in Tagalog or String Bean in English, but some people call it ''Snap Beans''. The yardlong bean is also known as the long-podded cowpea, asparagus bean, snake bean, or Chinese long bean. It is known as dau gok in Cantonese, thua fak yao in Thai and kacang panjang in Indonesian and Malay, sitaw Tagalog, bora in the West Indies and vali or eeril in Goa, India. Despite the name, the pods are actually only about half a yard long; the subspecies name sesquipedalis (one-and-a-half-foot-long) is a rather exact approximation of the pods length. Yardlong beans are quick-growing and daily checking/harvesting is often a necessity. The crisp, tender pods are eaten both fresh and cooked. They are at their best when young and slender. They are sometimes cut into short sections for cooking uses.')," +
                "('Squash','Description\n" +
                "Squash is the collective name given to several species of plant in the genus Cucurbita, including C. maxima, C. mixta , C. moschata and C. pepo, which are widely grown for their edible fruit. Squash plants are are herbaceous annual plants which are either trailing vines or bush-like in morphology. Vines generally have large, lobed leaves and long vines which can climb by attaching to surfaces with their tendrils. Bushes generally take up less space than the sprawling vine types and may have prickly leaves. Squash plants produce yellow or orange flowers and green, white or yellow fruit in a variety of shapes and sizes with smooth or ridged skin. Vining squash varieties can reach several meters in length and, as annuals, survive only one growing season. Squash originate from North and Central America and are referred to by their cultivar name e.g. acorn squash, butternut squash, spaghetti squash, zucchini, banana squash, hubbard squash and buttercup squash.\n" +
                "\n" +
                "PROPAGATION:\n" +
                "Basic requirements Squash is a warm-season crop, requiring lots of sun and good drainage to develop optimally and growing best at temperatures between 18 and 25°C (65–75°F). Squash will yield best if grown in a fertile, well-draining soil, rich in organic matter and with a pH between 6.5 and 7.5. Squash should be planted in full sun and provided with ample soil moisture due to their shallow root system. Vining varieties can grow to very large sizes and require a good deal of space. Smaller bush varieties are available for more modest spaces. Propagation Squash is propagated from seed and can be direct seeded or sown indoors and transplanted. If direct seeding,seeds should be sown after the last frosts and when the soil has warmed to at least 15.6°C (60°F). Sow 1–2 seeds 1.3–2.5 cm (0.5–1.0 in) deep, at least 90 cm (~3 ft) apart if growing bush varieties and 120–150 cm (4–5 ft) apart if growing vining varieties. Allow a further 1–3 m (6–10 ft) between rows depending on the variety. If transplanting, seeds should be sown 3–4 weeks before the last frost date in your area and transplanted before the plants develop their second set of true leaves. Seeds in sown both indoors and out require lightly moist soil for germination, care should be taken to avoid overwatering. Seeds should germinate in 5–10 days depending on the soil temperature. General care and maintenance Squash vines are sprawling and require plenty space to grow. Vines can be trained to grow on a trellis or fence. Squash also require a continuous supply of water and where drip irrigation is not being used, plants should be watered deeply once per week, providing at least an inch of water. Shallow watering or watering less frequently encourages a shallow root system. Mulches can be used to conserve soil moisture and black polyethylene mulch has the advantage of warming the soil. All squash varieties produce both male and female flowers (monoecious) and are pollinated by insects such as bees. Harvesting Squash is ready to harvest when the rind is hard and cannot be punctured with a fingernail. The skin of mature fruits is dull and dry in appearance, especially when compared with the shiny skin of an immature fruit.')," +
                "('Moringa Beans','Description/Taste\n" +
                "Malunggay is classified as a tropical plant that can reach a height of 9 meters. This tree is found growing in the Philippines, India and Africa. The most prized part of this tropical tree are the Malunggay pods, which contain essential oils, vitamins and nutrients. The Malunggay pods are very long, have a green to brown outer skin and contain small seeds that are winged and angled. Also known as Horseradish drumsticks, Malunggay pods have a flavor that is similar to the Green bean and all parts of the pod and even the tree are edible.\n" +
                "\n" +
                "Seasons/Availability:\n" +
                "Malunggay pods can be found growing year-round in tropical regions of the world.\n" +
                "\n" +
                "Current Facts:\n" +
                "The Malunggay, botanically known as Moringa oleifera is commonly referred to as the Miracle tree. The name \"Miracle\" is derived from the vast multi-use and multi-purpose nature of it''s parts.\n" +
                "\n" +
                "Nutritional Value:\n" +
                "Malunggay pods have been used for thousands of years as a medicinal herb and for its rich nutritional value. Malunggay pods have been shown to have numerous anti-cancer properties, which prevent cancer forming tumor cells. Other studies have shown the anti-inflammatory properties of the Malunggay pods can help to treat symptoms of arthritis, joint pain and rheumatism. Additionally, Malunggay pods have a very high content of vitamin C, A and vitamin B as well as calcium.')," +
                "('Water Spinach','Description/Taste\n" +
                "Kangkong leaves are medium to large in size and lanceolate or arrowhead-shaped, averaging 10-20 in length and 2-8 centimeters in diameter. The smooth, long green leaves grow in an alternate pattern and form on hollow stems that can grow up to 2 to 3 meters in length. These stems, or vines, are commonly found in aquatic locations and can float on the water and hold the leaves above the water line. Kangkong leaves are tender, and the stems are crunchy, offering a slippery texture when cooked and a mild, sweet, and nutty green flavor.\n" +
                "Seasons/Availability\n" +
                "Kangkong leaves are available year-round.\n" +
                "Current Facts\n" +
                "Kangkong leaves, botanically classified as Ipomoea aquatica, grow on an herbaceous, trailing vine that is found in humid, tropical lowlands and belongs to the Convolvulaceae, or morning glory family. Also known as Kangkung, Kankun, Chinese spinach, Water spinach, River spinach, and Swamp cabbage, Kangkong leaves are a popular leaf vegetable prized for its crunchy stems and tender leaves and can be found in most Southeast Asian cuisines.\n" +
                "Nutritional Value\n" +
                "Kangkong leaves are a good source of iron and calcium and also contains magnesium, phosphorous, manganese, copper, vitamins C and K, and zinc.')," +
                "('Lettuce','Description\n" +
                "Lettuce, Lactuca sativa, is a leafy herbaceous annual or biennial plant in the family Asteraceae grown for its leaves which are used as a salad green. The lettuce plant can vary greatly in size, shape and leaf type but generally, the leaves of the plant form a dense head or loose rosette. The stem of the plant is short, with larger leaves arranged at the bottom and becoming progressively smaller further up the stem. Leaves can be smooth or curly and are usually green or red in color. The lettuce plant can grow to a height of 30–100 cm (12–40 in) in height and is typically grown as an annual, harvested after only one growing season. Lettuce may be referred to as garden lettuce and is believed to originate from Asia Minor and the Middle East.\n" +
                "\n" +
                "Uses\n" +
                "Lettuce is primarily eaten raw as a salad green. Some varieties can be cooked and eaten as a vegetable.\n" +
                "\n" +
                "Propagation\n" +
                "Requirements Lettuce is a cool season crop which will grow optimally at daytime temperatures of 15–20°C (59–68°F). The plant can be grown in a wide range of soils as long as it is fertile and moisture retaining due to the small root system of the plant. It is often grown in alkaline soil (pH greater than 7.0) but will not tolerate acid soil. Heat tolerant varieties can be grown over the summer months and care should be taken to protect the leaves from strong sun by shading or covering to prevent the plants from bolting. Sowing seeds Lettuce seeds can be sown directly in the garden or field as soon as the soil can be worked as the seeds will germinate at temperatures of 4.4°C (40°F) and above and seedlings will tolerate a light frost. Seeds should be sown 0.3–0.6 cm (1/8–1/4 in) deep and 2.5 cm (1 in) apart, leaving 50 cm (20 in) between rows. Cover the seeds lightly, tamp the soil and water the seeds. Seedlings should emerge in 2–15 days. When the plants have 2–3 true leaves then they should be thinned to a final spacing of 25–45 cm (10 to 18 in) depending on the variety. Plant new seeds every 2–3 weeks for a continuous harvest. Transplants Sow seeds in seedling trays in a sterile seed starting mix at a rate of approximately 3–4 seeds per inch (2.5 cm). Young plants can be potted up into larger pots or cell trays when they are about 2 weeks old. Plant transplants in the garden after hardening off, spacing plants 25–45 cm (10 to 18 in) (depending on variety) and allowing 50 cm (20 in) between rows. Plant new seeds every 2–3 weeks for a continuous harvest.')," +
                "('Bottle Gourd','Description/Taste:\n" +
                "Bottle gourd can vary in size shape and length depending on how it is grown and when it is harvested. It can be short and round, uniformly cylindrical, curved, bulbous, or extremely long and thin. Its skin is most often smooth though there are some varieties that are covered in fine hairs. Its coloring can vary from a light green or chartreuse to dark green. The interior flesh is creamy white with petite seeds that when young are tender and edible but when more mature become hard and should be removed prior to consumption. Young bottle gourd squash offers a mild flavor reminiscent of summer squash and cucumber with a firm texture.\n" +
                "\n" +
                "Seasons/Availability:\n" +
                "Bottle gourd is found growing year-round in tropical climates.\n" +
                "\n" +
                "Current Facts:\n" +
                "Bottle gourd, botanically a part of Lagenaria siceraria is a vine and a member of the Cucurbitaceae family. When young it is utilized as a vegetable in preparations similar to that for squash and once mature it can be dried and hollowed out to make utensils, musical instruments and vessels. The Bottle gourd has a rich history and was one of the first cultivated plants in the world. It is known today by many different names throughout the world, most notably as a Calabash, Opo, Cucuzza and Long melon.\n" +
                "\n" +
                "Nutritional Value:\n" +
                "Bottle gourd is low in calories and provides small amounts of vitamin C, folate, calcium, iron, zinc and B vitamins. It is also rich in fiber and is believed to help aid in healthy digestion. The juice of Bottle gourd is touted for its vitamin C and zinc content as well as for its ability to potentially regulate blood sugar levels. In India the juice is popularly consumed as a health benefiting beverage. Caution should be used however as to never consume Bottle gourd juice that has developed a bitter flavor as it may contain toxins that can cause ulcers, extreme harm to the digestive track and in some cases even fatality.')," +
                "('Bitter Gourd','Bitter gourd (Momordica charantia) is one of the world’s major vegetable crops, which belongs to the family Cucurbitaceae. The genus Momordica is a native of the Paleotropics and comprises about 60 species. Bitter gourd grows in tropical and subtropical areas, including parts of East Africa, Asia, the Caribbean, and South America, where it is used not only as a food but also as a medicine. Two botanical varieties viz., var. charantia synonymous with large-fruited cultivated Chinese bitter melon and var. muricata representing small-fruited, predominantly wild forms were recognized. Wide variability was noticed especially among cultivated types for fruit and seed morphology. The plant is monoecious, annual climber with long-stalked leaves and yellow, solitary male and female flowers borne on the leaf axils. The warty and oblong or elliptical-shaped fruit is botanically a ‘pepo.’ The plant grows well in a variety of soils and begins flowering about one month after planting. It is used as a food, bitter flavoring, and medicine. Bitter gourd has a relatively high nutritional value due to high iron and ascorbic acid content. Indians have traditionally used the leaves and fruits as a medicine to treat diabetes, colic, and to heal skin sores and wounds. Bitter gourd is reported to possess antioxidant, antimicrobial, antiviral, and antidiabetic properties.')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        if(oldVersion < 2) {
            db.execSQL(ALTER_VEGETABLE_TABLE_1);
        }
        if(oldVersion < 3) {
            db.execSQL(CREATE_CATEGORY_TABLE);
            db.execSQL(ALTER_VEGETABLE_TABLE_2);
        }
        if(oldVersion < 4) {
            db.execSQL(ADD_IMAGE_COLUMN);
            Log.d("Success: ", "Successfully added image column in vegetables table!");
        }

        onCreate(db);
    }

    public VegetableModel fetchVegetableById(String id) {

        VegetableModel vegetable = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " +
                TABLE_NAME + "." + COL_1 + ", " +   //  vegetable id
                TABLE_NAME + "." + COL_2 + ", " +   //  vegetable name
                TABLE_NAME + "." + COL_3 + ", " +   //  vegetable description
                TABLE_NAME + "." + COLUMN_VEGETABLE_URL + ", " +
                TABLE_NAME + "." + COLUMN_CATEGORY_FK + ", " +
                CATEGORY_TABLE + "." + CATEGOR_NAME + ", " +
                TABLE_NAME + "." + COLUMN_VEGETABLE_IMAGE +
                " FROM " + TABLE_NAME +
                " LEFT JOIN " + CATEGORY_TABLE +
                " ON " +
                TABLE_NAME + "." + COLUMN_CATEGORY_FK + " = " + CATEGORY_TABLE + "." + CATEGORY_ID +
                " WHERE " + TABLE_NAME + "." + COL_1 + " = ?";

        Cursor result = db.rawQuery(query, new String [] { id });

        if(result.moveToFirst()) {
            int vegetableId = result.getInt(0);
            String name = result.getString(1);
            String description = result.getString(2);
            String tutorialURI = result.getString(3);
            int categoryId = result.getInt(4);
            String categoryName = result.getString(5);

            byte [] imageByte = result.getBlob(6);
            Bitmap vegetableImage = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
            vegetable = new VegetableModel(vegetableId, name, description, tutorialURI, categoryId, categoryName, vegetableImage);
        }
        return vegetable;
    }


    public ArrayList<VegetableModel> fetchVegetableByCategory(String category_id) {

        ArrayList<VegetableModel> vegetables = new ArrayList<>();
        VegetableModel model;

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT "+ COL_1 +", "+ COL_2 +" FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY_FK + " = ?";
            Cursor result = db.rawQuery(query, new String[] { category_id });

            while(result.moveToNext()) {
                int id = result.getInt(result.getColumnIndex(COL_1));
                String name = result.getString(result.getColumnIndex(COL_2));
//                String description = result.getString(result.getColumnIndex(COL_3));
//                String uri = result.getString(result.getColumnIndex(COLUMN_VEGETABLE_URL));
//
//                byte [] imageByte = result.getBlob(result.getColumnIndex(COLUMN_VEGETABLE_IMAGE));
//                Bitmap image = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);

                model = new VegetableModel(id, name, null, null, 0, null, null);
                vegetables.add(model);
            }
            db.close();
        }
        catch(Exception e) {
            Log.d("Error", e.toString());
        }

        return vegetables;
    }


    public ArrayList<CategoryModel> fetchCategories() {

        ArrayList<CategoryModel> categories = new ArrayList<>();
        CategoryModel category;

        String query = "SELECT * FROM " + CATEGORY_TABLE + " WHERE " +
                CATEGORY_ID + " < 5";

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor result = db.rawQuery(query, null);

            while(result.moveToNext()) {

                category = new CategoryModel(result.getString(0), result.getString(1));
                categories.add(category);
            }
            db.close();
        }
        catch(Exception e) {
            Log.d("Error", e.toString());
        }

        return categories;
    }


    public boolean addData(String name, String des){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, des);

        Log.d(TAG, "addData: Adding "+name+" to "+TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT "+COL_3+" FROM "+TABLE_NAME+" WHERE "+COL_1+" = "+id;
        String query = "SELECT "+COL_1+", "+COL_2+", "+COL_3+", "+COLUMN_VEGETABLE_URL+" FROM "+TABLE_NAME+" WHERE "+COL_1+" = "+id;
        Cursor cursor = db.rawQuery( query,null);
        return cursor;
    }

    public void inputTutorialURL() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv;
        Helper h = new Helper();

        for(int i = 0; i < 10; i++) {

            cv = new ContentValues();
            cv.put(COLUMN_VEGETABLE_URL, h.getUri(i+1));
            db.update(TABLE_NAME, cv, "ID=?", new String[] { String.valueOf(i+1) });
        }

        db.close();
    }

    public void populateCategoryTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CATEGOR_NAME, "JAN - MAR");
        db.insert(CATEGORY_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(CATEGOR_NAME, "APR - JUN");
        db.insert(CATEGORY_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(CATEGOR_NAME, "JUL - SEP");
        db.insert(CATEGORY_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(CATEGOR_NAME, "OCT - DEC");
        db.insert(CATEGORY_TABLE, null, cv);

        Log.d("TAG", "Category Populated");

        db.close();
    }

    public void updateVVegetableCategory() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv;
//        Jan - Mar =   1
//        Apr - Jun =   2
//        Jul - Sep =   3
//        Oct - Dec =   4


//        1-Eggplant    =   2
//        2-Kamatis     =   3
//        3-Repolyo      =   1
//        4-Sitaw       =   3
//        5-Squash      =   2
//        6-malunggay   =   1
//        7-kangkong    =   2
//        8-Lettuce      =   2
//        9-upo         =   4
//        10-ampalaya   =   4


        cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_FK, "2");
        db.update(TABLE_NAME, cv, "ID=?", new String[] { "1" });

        cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_FK, "3");
        db.update(TABLE_NAME, cv, "ID=?", new String[] { "2" });

        cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_FK, "1");
        db.update(TABLE_NAME, cv, "ID=?", new String[] { "3" });

        cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_FK, "3");
        db.update(TABLE_NAME, cv, "ID=?", new String[] { "4" });

        cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_FK, "2");
        db.update(TABLE_NAME, cv, "ID=?", new String[] { "5" });

        cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_FK, "1");
        db.update(TABLE_NAME, cv, "ID=?", new String[] { "6" });

        cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_FK, "2");
        db.update(TABLE_NAME, cv, "ID=?", new String[] { "7" });

        cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_FK, "2");
        db.update(TABLE_NAME, cv, "ID=?", new String[] { "8" });

        cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_FK, "4");
        db.update(TABLE_NAME, cv, "ID=?", new String[] { "9" });

        cv = new ContentValues();
        cv.put(COLUMN_CATEGORY_FK, "4");
        db.update(TABLE_NAME, cv, "ID=?", new String[] { "10" });

        db.close();
    }

    public void updateVegetableImage(String vegetableId, Bitmap image) {

        if(vegetableId == null)
            return;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();

            cv.put(COLUMN_VEGETABLE_IMAGE, byteArray);
            if(vegetableId != null) {
                long result = db.update(TABLE_NAME, cv, "ID=?", new String[] { vegetableId });

                if(result != -1) {
                    Log.d("Success", "Updated the image of VEGETABLE_ID = " + vegetableId);
                    db.close();
                }
                else {
                    Log.d("Error", "Could not update the vegetable image");
                }

            }
            db.close();
        }
        catch(Exception e) {
            Toast.makeText(cont, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
