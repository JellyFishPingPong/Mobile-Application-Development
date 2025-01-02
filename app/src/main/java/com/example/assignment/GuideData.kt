package com.example.assignment

class GuideData(
    val id: Int,
    val vegetableName: String,
    val image: Int,
    val method: String,
    val veg_content: String,
) {

    constructor(id: Int, vegetableName: String) : this(
        id, vegetableName, 0, "", ""
    )

    companion object {
        val veg_data = listOf<GuideData>(
            GuideData(
                1, "Carrot", R.drawable.carrot_image, "Propagate by seed",
                "\nMaintenance and Care :" +
                        "\n\nChoose the right time to plant: Carrots are a cool-season crop that grow best in temperatures between 60 and 70 degrees Fahrenheit. It\\'s best to plant carrots in early spring or fall.\n" +
                        "        \nSelect a location: Carrots prefer well-drained soil that is free of rocks and debris. The soil should be loose and friable, with a pH between 6.0 and 6.8. Carrots require full sun exposure, but they can tolerate some shade.\n" +
                        "        \n Prepare the soil: Before planting, remove any weeds and debris from the planting area. Loosen the soil to a depth of at least 12 inches, and work in compost or well-rotted manure.\n" +
                        "        \n Plant the seeds: Sow carrot seeds directly into the prepared soil. Plant the seeds about 1/4 inch deep and 2-4 inches apart in rows spaced 12-18 inches apart. Cover the seeds with soil and gently tamp it down.\n" +
                        "        \n Water the seeds: Keep the soil consistently moist until the seeds germinate, which can take up to 2 weeks. Water the seeds lightly every day or two, taking care not to disturb the soil.\n" +
                        "        \n Thin the seedlings: Once the seedlings have developed their first true leaves, thin them so that they are spaced about 1-2 inches apart. This will allow each carrot enough room to develop a full-size root.\n" +
                        "        \n Care for the plants: Carrots require consistent moisture to grow well, so water them deeply once a week. Mulch around the plants to help retain moisture and suppress weeds. Fertilize the plants every 4-6 weeks with a balanced fertilizer.\n" +
                        "        \n Harvest the carrots: Carrots are ready to harvest when they reach their mature size, usually about 2-3 months after planting. Gently loosen the soil around the base of the carrot, and pull it up by the greens."
            ),
            GuideData(
                2, "Potato", R.drawable.potato_image, "Propagate by small piece potato",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Potatoes are a cool-season crop that grow best in temperatures between 60 and 70 degrees Fahrenheit. It\\'s best to plant potatoes in early spring, about 2-4 weeks before the last expected frost in your area.\n" +
                        "        \n Select a location: Potatoes prefer well-drained soil that is loose and fertile. The soil should have a pH between 5.5 and 6.0. Potatoes require full sun exposure.\n" +
                        "        \n Prepare the soil: Before planting, remove any weeds and debris from the planting area. Loosen the soil to a depth of at least 12 inches, and work in compost or well-rotted manure.\n" +
                        "        \n Prepare the seed potatoes: About 1-2 weeks before planting, place the seed potatoes in a warm, well-lit location to encourage sprouting. Cut larger seed potatoes into pieces that are about 2 inches in size, making sure each piece has at least one \"eye,\" or bud.\n" +
                        "        \n Plant the seed potatoes: Plant the seed potatoes about 4-6 inches deep and 12-18 inches apart in rows spaced 24-36 inches apart. Cover the seed potatoes with soil and gently tamp it down.\n" +
                        "        \n Hill the plants: As the potato plants grow, mound soil up around the stems to keep the developing tubers covered. This will prevent the tubers from turning green and becoming inedible.\n" +
                        "        \n Water the plants: Potatoes require consistent moisture to grow well, so water them deeply once a week. Avoid overwatering, which can lead to disease.\n" +
                        "        \n Control pests and diseases: Potatoes are prone to certain pests and diseases, so monitor your plants regularly and take action if you notice any issues. Use organic methods of pest control when possible.\n" +
                        "        \n Harvest the potatoes: Potatoes are ready to harvest when the foliage dies back, which usually occurs 2-3 months after planting. Carefully dig up the plants and remove the potatoes from the soil. Allow the potatoes to dry in a cool, dark location for a few days before storing them."
            ),
            GuideData(
                3, "Cabbage", R.drawable.cabbage_image, "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Cabbage is a cool-season crop that grows best in temperatures between 60 and 65 degrees Fahrenheit. It\\'s best to plant cabbage in early spring or fall.\n" +
                        "        \n Select a location: Cabbage prefers well-drained soil that is rich in organic matter. The soil should have a pH between 6.0 and 7.5. Cabbage requires full sun exposure, but it can tolerate some shade.\n" +
                        "        \n Prepare the soil: Before planting, remove any weeds and debris from the planting area. Loosen the soil to a depth of at least 12 inches, and work in compost or well-rotted manure.\n" +
                        "        \n Plant the seeds: Sow cabbage seeds directly into the prepared soil, or start them indoors 4-6 weeks before the last expected frost in your area. Plant the seeds about 1/4 inch deep and 2-3 inches apart in rows spaced 18-24 inches apart. Cover the seeds with soil and gently tamp it down.\n" +
                        "        \n Thin the seedlings: Once the seedlings have developed their first true leaves, thin them so that they are spaced about 12-18 inches apart. This will allow each cabbage enough room to develop a full-sized head.\n" +
                        "        \n Care for the plants: Cabbage requires consistent moisture to grow well, so water them deeply once a week. Mulch around the plants to help retain moisture and suppress weeds. Fertilize the plants every 4-6 weeks with a balanced fertilizer.\n" +
                        "        \n Control pests and diseases: Cabbage is prone to certain pests and diseases, so monitor your plants regularly and take action if you notice any issues. Use organic methods of pest control when possible.\n" +
                        "        \n Harvest the cabbage: Cabbage is ready to harvest when the heads are firm and tight. Cut the head off the plant with a sharp knife, leaving a few inches of stem attached to the plant. Some varieties may produce smaller side heads after the main head is harvested, so keep an eye out for those as well."
            ),
            GuideData(
                4, "Broccoli", R.drawable.broccoli_image, "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Broccoli is a cool-season crop, and it grows best when the temperature ranges between 60 and 65 degrees Fahrenheit. It\\'s best to plant broccoli in early spring or fall.\n" +
                        "        \n Select a location: Broccoli needs a location with full sun exposure and well-drained soil. The soil should be rich in organic matter and have a pH between 6.0 and 7.0.\n" +
                        "        \n Prepare the soil: Before planting, remove any weeds and debris from the planting area. Loosen the soil to a depth of 8-10 inches and work in compost or well-rotted manure.\n" +
                        "        \n Plant the broccoli: Space broccoli plants 18-24 inches apart, with rows spaced 24-36 inches apart. Dig a hole that is slightly larger than the root ball of the plant. Place the plant in the hole and gently pack the soil around the base.\n" +
                        "        \n Water the plants: Broccoli needs consistent moisture to grow well. Water the plants deeply after planting and then once a week, providing 1-1.5 inches of water each time.\n" +
                        "        \n Fertilize the plants: Apply a balanced fertilizer to the soil around the plants every 4-6 weeks. Alternatively, you can work in a slow-release fertilizer at the time of planting.\n" +
                        "        \n Control pests and diseases: Broccoli is prone to certain pests and diseases, so monitor your plants regularly and take action if you notice any issues. Use organic methods of pest control when possible.\n" +
                        "        \n Harvest the broccoli: Harvest the broccoli heads when they are firm and tight. Cut the head off the plant with a sharp knife, leaving a few inches of stem attached to the plant. Some varieties may produce smaller side shoots after the main head is harvested, so keep an eye out for those as well."
            ),
            GuideData(
                5,
                "Lady Finger (Okra)",
                R.drawable.lady_finger_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Okra is a warm-season crop that grows best in temperatures between 70 and 95 degrees Fahrenheit. It\\'s best to plant okra after the last expected frost in your area.\n" +
                        "        \n Select a location: Okra prefers well-drained soil that is rich in organic matter. The soil should have a pH between 6.0 and 7.5. Okra requires full sun exposure.\n" +
                        "        \n Prepare the soil: Before planting, remove any weeds and debris from the planting area. Loosen the soil to a depth of at least 12 inches, and work in compost or well-rotted manure.\n" +
                        "        \n Plant the seeds: Sow okra seeds directly into the prepared soil, planting them about 1 inch deep and 12-18 inches apart in rows spaced 24-36 inches apart. Cover the seeds with soil and gently tamp it down.\n" +
                        "        \n Thin the seedlings: Once the seedlings have developed their first true leaves, thin them so that they are spaced about 12-18 inches apart. This will allow each okra enough room to grow and produce fruit.\n" +
                        "        \n Care for the plants: Okra requires consistent moisture to grow well, so water them deeply once a week. Mulch around the plants to help retain moisture and suppress weeds. Fertilize the plants every 4-6 weeks with a balanced fertilizer.\n" +
                        "        \n Control pests and diseases: Okra is prone to certain pests and diseases, so monitor your plants regularly and take action if you notice any issues. Use organic methods of pest control when possible.\n" +
                        "        \n Harvest the okra: Okra is ready to harvest when the pods are about 2-4 inches long and still tender. Cut the pods off the plant with a sharp knife or scissors, being careful not to damage the stem or leaves. Harvest the okra regularly to encourage continued production."
            ),GuideData(
                6,
                "Tomato",
                R.drawable.tomato_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nStart seeds indoors: Tomatoes require a longer growing season, so it's best to start the seeds indoors 6-8 weeks before the last expected frost in your area. Fill seed trays or small pots with seed-starting mix. Plant two to three seeds per container, about ¼ inch deep. Water the soil lightly and cover the containers with plastic wrap or a humidity dome to create a moist environment.\n" +
                        "\nProvide optimal conditions: Place the seed trays or pots in a warm location with temperatures between 70 and 80 degrees Fahrenheit. Once the seeds germinate and seedlings emerge, remove the plastic cover and place them in a location with bright, indirect light or under grow lights. Maintain a temperature of 65 to 75 degrees Fahrenheit.\n" +
                        "\nTransplant seedlings: When the seedlings have developed their second set of true leaves, they are ready to be transplanted. Harden off the seedlings by gradually exposing them to outdoor conditions over a week. Transplant them into larger containers or directly into the garden soil.\n" +
                        "\nPrepare the planting area: Choose a sunny location with well-drained soil. Remove weeds and loosen the soil using a garden fork or tiller. Incorporate compost or well-rotted manure to enrich the soil.\n" +
                        "\nPlant the seedlings: Dig a hole deep enough to cover the seedling up to its first set of leaves. Gently remove the seedling from its container, being careful not to damage the roots. Place the seedling in the hole and backfill with soil, firming it gently around the base.\n" +
                        "\nWater the seedlings: Water the newly transplanted seedlings thoroughly to settle the soil around the roots. Provide regular watering, keeping the soil consistently moist but not waterlogged. Water at the base of the plants to avoid wetting the leaves, which can encourage disease.\n" +
                        "\nProvide support: If you are growing indeterminate tomato varieties (vining types), provide stakes, cages, or trellises to support the plants as they grow.\n" +
                        "\nMaintain care: Monitor the plants for pests, diseases, and nutrient deficiencies. Apply organic or appropriate pest control measures if necessary. Fertilize the plants with a balanced tomato fertilizer following the instructions on the package.\n" +
                        "\nHarvest tomatoes: Depending on the variety, tomatoes typically mature and are ready for harvest 60 to 85 days after transplanting. Harvest when the fruits have reached their desired color and are firm but slightly soft to the touch. Twist or cut the tomatoes from the vine using clean, sharp pruners or scissors."
            ),
            GuideData(
                7,
                "Cucumber",
                R.drawable.cucumber_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Cucumbers are warm-season crops that require temperatures above 60°F (15°C) for germination and growth. Wait until all danger of frost has passed and the soil has warmed up before planting. Typically, cucumbers are planted in spring when the soil has warmed.\n" +
                        "\nPrepare the soil: Cucumbers prefer well-drained soil with good organic matter content. Choose a location with full sun exposure. Work the soil to a depth of about 8-10 inches, removing any weeds, rocks, or debris. Incorporate compost or well-rotted manure to improve the soil's fertility and structure.\n" +
                        "\nPlant the seeds: Cucumber seeds can be directly sown into the prepared soil. Plant the seeds about 1 inch deep and space them according to the specific variety you are growing. For bush varieties, space the seeds about 12-24 inches apart in rows, with rows spaced about 36-48 inches apart. For vining varieties, space the seeds about 36-48 inches apart in rows, with rows spaced about 48-72 inches apart.\n" +
                        "\nProvide support (for vining varieties): If you are growing vining cucumber varieties, it's beneficial to provide support for the plants to climb. This can be done by installing trellises, stakes, or other structures for the vines to climb as they grow.\n" +
                        "\nWater the seeds: After planting, water the seeds gently to ensure good soil contact. Keep the soil consistently moist but not waterlogged throughout the germination period, which usually takes about 7-14 days. Once the plants have established, water deeply and regularly, especially during hot, dry weather.\n" +
                        "\nMulch and weed control: Applying a layer of organic mulch, such as straw or wood chips, around the base of the cucumber plants can help suppress weeds, retain soil moisture, and regulate soil temperature. Keep the area around the plants free of weeds, as they can compete for nutrients, water, and sunlight.\n" +
                        "\nFertilize the plants: Cucumbers are heavy feeders and benefit from regular fertilization. Before planting, incorporate a balanced fertilizer into the soil based on the recommendations of a soil test. Additionally, you can side-dress the plants with compost or a balanced fertilizer during the growing season to provide additional nutrients.\n" +
                        "\nMonitor pests and diseases: Watch for common pests, such as cucumber beetles, aphids, or powdery mildew. Use organic or appropriate pest control measures as needed. Monitor the plants for diseases and take action if necessary, such as applying fungicides or removing affected plant parts.\n" +
                        "\nHarvest the cucumbers: Cucumbers can be harvested when they have reached the desired size and are firm, crisp, and green. This typically occurs about 50-70 days after planting, depending on the variety. Cut or twist the cucumbers from the vines, being careful not to damage the plant. Harvest regularly to encourage continued fruit production."
            ),
            GuideData(
                8,
                "Green Beans",
                R.drawable.green_beans_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Green beans are warm-season crops that do not tolerate frost. Wait until all danger of frost has passed and the soil temperature has warmed to at least 60°F (15°C) before planting. Typically, green beans are planted in spring when the soil has warmed up.\n" +
                        "\nPrepare the soil: Green beans prefer well-drained soil with good organic matter content. Choose a location with full sun exposure. Work the soil to a depth of about 6-8 inches, removing any weeds, rocks, or debris. Incorporate compost or well-rotted manure to enrich the soil and improve its fertility and structure.\n" +
                        "\nPlant the seeds: Green beans can be directly sown into the prepared soil. Plant the seeds about 1 inch deep and space them according to the specific variety you are growing. Bush bean varieties can be spaced about 2-4 inches apart in rows, with rows spaced about 18-24 inches apart. Pole bean varieties require support and can be spaced about 6-8 inches apart in rows, with rows spaced about 30-36 inches apart.\n" +
                        "\nWater the seeds: After planting, water the seeds gently to ensure good soil contact. Keep the soil consistently moist throughout the germination period, which usually takes about 7-14 days. Avoid overwatering or letting the soil dry out excessively.\n" +
                        "\nProvide support (for pole beans): If you are growing pole beans, provide support for the plants to climb. This can be done by installing trellises, stakes, or other structures that the vines can wrap around as they grow.\n" +
                        "\nMaintain care: Monitor the plants for pests, such as aphids or bean beetles, and apply organic or appropriate pest control measures as needed. Keep the area around the green bean plants free of weeds, as they can compete for nutrients and water. Mulching can help suppress weeds and retain soil moisture.\n" +
                        "\nHarvest the green beans: Green beans can be harvested when the pods are firm, crisp, and have reached the desired size. This typically occurs about 50-70 days after planting, depending on the variety. Harvest the beans by gently snapping or cutting the pods from the plants. Be careful not to damage the plants or neighboring pods."
            ),
            GuideData(
                9,
                "Eggplant",
                R.drawable.eggplant_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nStart seeds indoors: Eggplants are warm-season crops and require a long growing season. In regions with short growing seasons, it's recommended to start eggplant seeds indoors 8-10 weeks before the last expected frost date. Use seed trays or pots filled with seed-starting mix.\n" +
                        "\nSow the seeds: Plant 2-3 seeds per cell or pot, about ¼ to ½ inch deep. Cover the seeds with a thin layer of soil and lightly press it down. Water gently to keep the soil moist but not waterlogged.\n" +
                        "\nProvide warmth and light: Eggplant seeds germinate best in warm temperatures, around 70-85°F (21-29°C). Place the seed trays or pots in a warm location or use a seedling heat mat to maintain consistent warmth. Additionally, provide adequate light by placing them in a sunny window or using fluorescent grow lights.\n" +
                        "\nTransplant the seedlings: Once the seedlings have developed their first true leaves and all risk of frost has passed, they can be transplanted into the garden or larger containers. Harden off the seedlings by gradually exposing them to outdoor conditions over a period of 7-10 days.\n" +
                        "\nPrepare the soil: Choose a sunny location with well-drained soil. Work the soil to a depth of about 8-10 inches, removing any weeds, rocks, or debris. Incorporate compost or well-rotted manure to improve the soil's fertility and structure.\n" +
                        "\nPlant the seedlings: Dig holes in the prepared soil that are slightly larger than the root balls of the seedlings. Space the seedlings about 18-24 inches apart in rows, with rows spaced about 24-36 inches apart. Place the seedlings in the holes and backfill with soil, gently firming it around the roots.\n" +
                        "\nWater the seedlings: After planting, water the seedlings thoroughly to settle the soil and ensure good root-to-soil contact. Keep the soil consistently moist but not waterlogged throughout the growing season. Water at the base of the plants rather than overhead to prevent foliage diseases.\n" +
                        "\nMulch and support (optional): Applying mulch around the base of the plants can help suppress weeds, retain soil moisture, and regulate soil temperature. Some larger eggplant varieties may benefit from staking or support to keep the plants upright.\n" +
                        "\nFertilize the plants: Eggplants benefit from regular fertilization. Before planting, incorporate a balanced fertilizer into the soil based on the recommendations of a soil test. You can also side-dress the plants with compost or a balanced fertilizer during the growing season to provide additional nutrients.\n" +
                        "\nMonitor pests and diseases: Watch for common pests, such as aphids, flea beetles, or Colorado potato beetles. Use organic or appropriate pest control measures as needed. Monitor the plants for diseases such as fungal infections or bacterial wilt and take action if necessary.\n" +
                        "\nHarvest the eggplants: Eggplants can be harvested when the fruits have reached their mature size and are glossy and firm. This typically occurs about 60-80 days after transplanting, depending on the variety. Use a sharp knife or pruning shears to cut the fruits from the plants, leaving a short stem attached."
            ),
            GuideData(
                10,
                "Pumpkin",
                R.drawable.pumpkin_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Pumpkins are warm-season crops that require a long growing season. Wait until all danger of frost has passed and the soil temperature has warmed to at least 60°F (15°C) before planting. Typically, pumpkins are planted in spring when the soil has warmed up.\n" +
                        "\nPrepare the soil: Pumpkins prefer well-drained soil with good organic matter content. Choose a location with full sun exposure. Work the soil to a depth of about 12-18 inches, removing any weeds, rocks, or debris. Incorporate compost or well-rotted manure to enrich the soil and improve its fertility and structure.\n" +
                        "\nPlant the seeds: Plant pumpkin seeds directly into the prepared soil. Plant 2-3 seeds together in small mounds or hills, spacing the hills about 4-6 feet apart to allow for proper vine growth. Plant the seeds about 1 inch deep. If you're planting multiple rows, space the rows about 6-8 feet apart.\n" +
                        "\nProvide proper spacing: Once the seedlings emerge and develop their first true leaves, thin them out, leaving the strongest plant in each hill. Proper spacing is essential to allow the vines to spread and for each plant to receive adequate sunlight, nutrients, and water.\n" +
                        "\nWater the seeds: After planting, water the seeds thoroughly to ensure good soil contact. Keep the soil consistently moist but not waterlogged throughout the growing season. Provide deep watering to encourage deep root development. Water at the base of the plants rather than overhead to prevent foliage diseases.\n" +
                        "\nProvide support (optional): Depending on the pumpkin variety and the space available, you may choose to provide support for the vines. You can use trellises, stakes, or other support structures to keep the vines off the ground and prevent them from sprawling.\n" +
                        "\nFertilize the plants: Pumpkins are heavy feeders and benefit from regular fertilization. Before planting, incorporate a balanced fertilizer into the soil based on the recommendations of a soil test. You can also side-dress the plants with compost or a balanced fertilizer during the growing season to provide additional nutrients.\n" +
                        "\nControl weeds: Keep the area around the pumpkin plants free of weeds, as they can compete for nutrients, water, and sunlight. Mulching with organic material, such as straw or wood chips, can help suppress weeds, retain soil moisture, and regulate soil temperature.\n" +
                        "\nMonitor pests and diseases: Watch for common pests, such as squash bugs, cucumber beetles, or vine borers. Use organic or appropriate pest control measures as needed. Monitor the plants for diseases such as powdery mildew or fungal infections and take action if necessary.\n" +
                        "\nHarvest the pumpkins: Pumpkins are ready for harvest when the fruits have reached full color, the rinds are hard, and the stems have started to dry out and crack. This typically occurs about 75-120 days after planting, depending on the variety. Cut the pumpkins from the vines using a sharp knife or shears, leaving a few inches of stem attached."
            ),
            GuideData(
                11,
                "Lettuce",
                R.drawable.lettuce_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Lettuce is a cool-season crop that prefers cooler temperatures. It can be grown in both spring and fall. For spring planting, sow seeds as soon as the soil can be worked, a few weeks before the last expected frost. For fall planting, sow seeds about 6-8 weeks before the first expected fall frost.\n" +
                        "\nPrepare the soil: Lettuce prefers well-drained soil with good organic matter content. Work the soil to a depth of about 6-8 inches, removing any weeds and debris. Incorporate compost or well-rotted manure to improve the soil's fertility and structure.\n" +
                        "\nPlant the seeds: Sow the lettuce seeds directly into the prepared soil. Plant the seeds about ¼ to ½ inch deep and space them according to the specific variety you are growing. Leaf lettuce varieties can be spaced about 4-6 inches apart, while head lettuce varieties may require more space. You can also broadcast the seeds over a prepared bed and thin the seedlings later to the desired spacing.\n" +
                        "\nWater the seeds: After planting, water the seeds gently to ensure good soil contact. Keep the soil consistently moist throughout the germination period, which usually takes about 7-14 days. Avoid overwatering or letting the soil dry out excessively.\n" +
                        "\nProvide shade (optional): Lettuce prefers cooler temperatures and can bolt (go to seed) quickly in hot weather. To prevent premature bolting, you can provide shade for the plants by using row covers, shade cloth, or planting them in a location that receives partial shade during the hottest part of the day.\n" +
                        "\nMaintain care: Monitor the plants for pests, such as slugs or aphids, and apply organic or appropriate pest control measures as needed. Keep the area around the lettuce plants free of weeds, as they can compete for nutrients and water. Mulching can help suppress weeds and retain soil moisture.\n" +
                        "\nHarvest the lettuce: Lettuce leaves can be harvested when they have reached a desirable size. Leaf lettuce varieties can be harvested by picking individual leaves from the outer parts of the plant, allowing the inner leaves to continue growing. Head lettuce varieties can be harvested by cutting the entire head just above the soil level. New leaves will continue to grow from the center of the plant, allowing for multiple harvests."
            ),
            GuideData(
                12,
                "Spinach",
                R.drawable.spinach_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Spinach is a cool-season crop that prefers cooler temperatures. It can be grown in both spring and fall. For spring planting, sow seeds as soon as the soil can be worked, a few weeks before the last expected frost. For fall planting, sow seeds about 6-8 weeks before the first expected fall frost.\n" +
                        "\nPrepare the soil: Spinach prefers well-drained soil with good organic matter content. Work the soil to a depth of about 6-8 inches, removing any weeds and debris. Incorporate compost or well-rotted manure to improve the soil's fertility and structure.\n" +
                        "\nPlant the seeds: Sow the spinach seeds directly into the prepared soil. Plant the seeds about ½ inch deep and space them about 2-4 inches apart in rows. Leave about 12-18 inches of space between rows. You can also broadcast the seeds over a prepared bed and thin the seedlings later to the desired spacing.\n" +
                        "\nWater the seeds: After planting, water the seeds gently to ensure good soil contact. Keep the soil consistently moist throughout the germination period, which usually takes about 7-14 days. Avoid overwatering or letting the soil dry out excessively.\n" +
                        "\nProvide shade (optional): Spinach prefers cooler temperatures and can bolt (go to seed) quickly in hot weather. To prevent premature bolting, you can provide shade for the plants by using row covers, shade cloth, or planting them in a location that receives partial shade during the hottest part of the day.\n" +
                        "\nMaintain care: Monitor the plants for pests, such as aphids or leafminers, and apply organic or appropriate pest control measures as needed. Keep the area around the spinach plants free of weeds, as they can compete for nutrients and water. Mulching can help suppress weeds and retain soil moisture.\n" +
                        "\nHarvest the spinach: Spinach can be harvested when the leaves have reached a desirable size, typically 35-50 days after planting, depending on the variety. Harvest outer leaves individually or cut the entire plant just above the soil level. New leaves will continue to grow, allowing for multiple harvests."
            ),
            GuideData(
                13,
                "Corn",
                R.drawable.corn_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Corn is a warm-season crop that requires a long growing season and warm soil. Wait until the soil temperature reaches at least 50-55 degrees Fahrenheit (10-13 degrees Celsius) before planting. Planting is typically done in spring when the soil has warmed up.\n" +
                        "\nPrepare the soil: Corn requires fertile soil with good drainage. Choose a location with full sun exposure. Work the soil to a depth of about 8-12 inches, removing any weeds, rocks, or debris. Incorporate compost or well-rotted manure to enrich the soil and improve its structure.\n" +
                        "\nPlant the seeds: Corn is wind-pollinated and should be planted in blocks or multiple rows rather than single rows to ensure good pollination. Plant corn seeds in a grid pattern, spacing them about 10-12 inches apart in all directions. Plant the seeds about 1-2 inches deep.\n" +
                        "\nAllow for proper spacing: Corn plants should be spaced about 24-36 inches apart in rows. This allows enough space for each plant to grow and develop properly.\n" +
                        "\nProvide adequate water: Corn requires regular and consistent watering, especially during periods of dry weather. Water the plants deeply, providing about 1 inch of water per week. Water at the base of the plants, avoiding wetting the leaves to reduce the risk of disease.\n" +
                        "\nFertilize the plants: Corn is a heavy feeder and benefits from regular fertilization. Before planting, incorporate a balanced fertilizer into the soil based on the recommendations of a soil test. Side-dress the plants with nitrogen fertilizer once they reach about knee height and again when the tassels form.\n" +
                        "\nControl weeds: Keep the area around the corn plants free of weeds, as they can compete for nutrients, water, and sunlight. Mulching with organic material can help suppress weeds and retain soil moisture.\n" +
                        "\nMonitor pests and diseases: Watch for common pests such as corn earworms, cutworms, or corn borers. Use organic or appropriate pest control measures as needed. Monitor the plants for diseases such as fungal infections or bacterial blights and take action if necessary.\n" +
                        "\nHarvest the corn: Corn is ready for harvest when the ears are fully developed and the kernels are plump and milky. This typically occurs about 60-90 days after planting, depending on the variety. To check for maturity, gently peel back the husk and press a kernel with your thumbnail. If the liquid is milky and opaque, the corn is ready for harvest. Twist the ears downward and pull them off the stalks."
            ),
            GuideData(
                14,
                "Peas",
                R.drawable.peas_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Peas are a cool-season crop that prefers cooler temperatures. They can be planted as soon as the soil can be worked in early spring, even before the last expected frost. Alternatively, you can plant them in late summer for a fall harvest.\n" +
                        "\nPrepare the soil: Peas prefer well-drained soil with good organic matter content. Work the soil to a depth of about 6-8 inches, removing any weeds and debris. Incorporate compost or well-rotted manure to improve the soil's fertility and structure.\n" +
                        "\nPlant the seeds: Dig a trench or furrow in the prepared soil, about 2 inches deep. Space the seeds about 2-3 inches apart along the trench, keeping rows spaced about 12-24 inches apart. Cover the seeds with soil and gently press it down.\n" +
                        "\nProvide support: Pea plants are climbers and benefit from support. As the plants grow, provide them with trellises, stakes, or a fence for them to climb on. This helps keep the plants upright and makes harvesting easier.\n" +
                        "\nWater the seeds: After planting, water the seeds thoroughly to ensure good soil contact. Keep the soil consistently moist but not waterlogged throughout the growing season. Peas have shallow root systems and require regular watering, especially during dry spells.\n" +
                        "\nMaintain care: Monitor the plants for pests, such as aphids or pea weevils, and apply organic or appropriate pest control measures as needed. Peas generally do not require much fertilization, especially if the soil has been enriched with compost. However, if growth seems slow or the plants appear pale, you can apply a balanced fertilizer according to the package instructions.\n" +
                        "\nHarvest the peas: Peas are ready for harvest when the pods are well-filled and the peas inside have reached the desired size. Harvesting time varies depending on the variety, but it typically occurs about 60-70 days after planting. Use scissors or your fingers to pick the pods, being careful not to damage the plants. Harvest regularly to encourage continued production."
            ),
            GuideData(
                15,
                "Celery",
                R.drawable.celery_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nStart seeds indoors: Celery seeds have a slow germination process, so it's best to start them indoors about 10-12 weeks before the last expected frost in your area. Use seed trays or small pots filled with seed-starting mix. Plant the seeds on the surface of the soil and lightly press them in, as celery seeds need light to germinate. Do not cover them with soil.\n" +
                        "\nProvide optimal conditions: Celery seeds require specific conditions to germinate successfully. Keep the seeds in a location with a temperature of around 70 to 75 degrees Fahrenheit. To improve germination rates, cover the seed trays or pots with a plastic cover or place them in a plastic bag to maintain high humidity. Check regularly for signs of germination.\n" +
                        "\nTransplant seedlings: Once the celery seedlings have developed their true leaves and are about 6-8 weeks old, they can be transplanted into individual pots or into the garden. Harden off the seedlings by gradually exposing them to outdoor conditions over a week.\n" +
                        "\nPrepare the planting area: Celery prefers fertile, well-drained soil with a pH between 6.0 and 7.0. Choose a location with full sun or partial shade. Amend the soil with compost or well-rotted manure to improve its fertility and drainage.\n" +
                        "\nPlant the seedlings: Dig holes in the prepared soil that are deep enough to accommodate the seedlings, allowing the crowns to be slightly above the soil level. Space the seedlings about 10-12 inches apart, with rows spaced 24-36 inches apart. Place the seedlings in the holes and backfill with soil, gently firming it around the base.\n" +
                        "\nWater the seedlings: Celery requires consistent moisture to grow well. Water the seedlings deeply after transplanting to settle the soil around the roots. Keep the soil consistently moist but not waterlogged throughout the growing season. Mulching around the plants can help retain moisture and suppress weeds.\n" +
                        "\nMaintain care: Celery is a heavy feeder and benefits from regular fertilization. Apply a balanced fertilizer every few weeks, following the package instructions. Keep an eye out for pests, such as aphids or slugs, and apply organic or appropriate pest control measures as needed. Additionally, celery plants may require blanching to improve their flavor and tenderness. This involves partially covering the stalks with soil or using celery collars to shield them from sunlight.\n" +
                        "\nHarvest celery: Celery can be harvested once the stalks have reached a desirable size and the plants have matured, usually around 85-120 days after planting. Cut the stalks at the base using a sharp knife or shears, being careful not to damage neighboring plants."
            ),
            GuideData(
                16,
                "Brussels Sprout",
                R.drawable.brussels_sprout_image,
                "Propagate by seed",
                "\nMaintenance and Care" +
                        "\n\nChoose the right time to plant: Brussels sprouts are a cool-season crop that requires a long growing season. Start the seeds indoors 12-14 weeks before the last expected frost in your area, or sow the seeds directly in the garden about 12 weeks before the first expected fall frost.\n" +
                        "\nStart seeds indoors: Fill seed trays or small pots with seed-starting mix. Plant two to three seeds per container, about ¼ inch deep. Water the soil lightly and cover the containers with plastic wrap or a humidity dome to create a moist environment.\n" +
                        "\nProvide optimal conditions: Place the seed trays or pots in a cool location with temperatures between 60 and 70 degrees Fahrenheit. Once the seeds germinate and seedlings emerge, remove the plastic cover and place them in a location with bright, indirect light or under grow lights. Maintain a temperature of 55 to 65 degrees Fahrenheit.\n" +
                        "\nTransplant seedlings: When the seedlings have developed their second or third set of true leaves and are about 6-8 weeks old, they are ready to be transplanted. Harden off the seedlings by gradually exposing them to outdoor conditions over a week. Transplant them into larger containers or directly into the garden soil.\n" +
                        "\nPrepare the planting area: Choose a location with full sun exposure and well-drained soil. Brussels sprouts prefer fertile, loamy soil with a pH between 6.0 and 7.5. Remove weeds and loosen the soil using a garden fork or tiller. Incorporate compost or well-rotted manure to improve the soil's fertility and drainage.\n" +
                        "\nPlant the seedlings: Dig holes that are deep enough to accommodate the seedlings, allowing for the first set of leaves to be above the soil level. Space the plants about 18-24 inches apart, with rows spaced 24-36 inches apart. Place the seedlings in the holes, backfill with soil, and firm it gently around the base.\n" +
                        "\nWater the seedlings: Water the seedlings thoroughly after transplanting to settle the soil around the roots. Provide regular watering to keep the soil consistently moist but not waterlogged. Water at the base of the plants to avoid wetting the leaves, which can encourage disease.\n" +
                        "\nMaintain care: Monitor the plants for pests, diseases, and nutrient deficiencies. Apply organic or appropriate pest control measures if necessary. Fertilize the plants every 4-6 weeks with a balanced fertilizer.\n" +
                        "\nHarvest Brussels sprouts: Brussels sprouts develop as small, cabbage-like heads along the stems. Harvest the sprouts from the bottom of the plant upward as they reach a suitable size, typically when they are about 1 to 2 inches in diameter. Twist or cut the sprouts off the stem using clean, sharp pruners or scissors."
            )
        )
    }

    val getVeg get() = veg_data
}