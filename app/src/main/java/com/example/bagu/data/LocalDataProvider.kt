package com.example.bagu.data

import com.example.bagu.R
import com.example.bagu.model.Recommendation
import com.example.bagu.data.Category.CoffeeShop
import com.example.bagu.data.Category.Restaurant
import com.example.bagu.data.Category.ShoppingCenter
import com.example.bagu.data.Category.Park

object LocalDataProvider {
    val allRecommendations = listOf(
        CoffeeShop(
            recommendations = listOf(
                Recommendation(
                    name = R.string.cafe_de_angelo,
                    information = R.string.cafe_de_angelo_info,
                    address = R.string.cafe_de_angelo_address,
                    contactNumber = R.string.cafe_de_angelo_contact_number,
                    images = listOf(
                        R.drawable.cafe_de_angelo_1,
                        R.drawable.cafe_de_angelo_2
                    )
                ),
                Recommendation(
                    name = R.string.cafe_by_the_ruins,
                    information = R.string.cafe_by_the_ruins_info,
                    address = R.string.cafe_by_the_ruins_address,
                    contactNumber = R.string.cafe_by_the_ruins_contact_number,
                    images = listOf(
                        R.drawable.cafe_by_the_ruins_1,
                        R.drawable.cafe_by_the_ruins_2
                    )
                ),
                Recommendation(
                    name = R.string.cafe_yagam,
                    information = R.string.cafe_yagam_info,
                    address = R.string.cafe_yagam_address,
                    images = listOf(
                        R.drawable.cafe_yagam_1,
                        R.drawable.cafe_yagam_2
                    )
                )
            )
        ),
        Restaurant(
            recommendations = listOf(
                Recommendation(
                    name = R.string.ihawjuan,
                    information = R.string.ihawjuan_info,
                    address = R.string.ihawjuan_address,
                    contactNumber = R.string.ihawjuan_contact_number,
                    images = listOf(
                        R.drawable.ihawjuan_1,
                        R.drawable.ihawjuan_2
                    )
                ),
                Recommendation(
                    name = R.string.te_quiero_tapas_bar,
                    information = R.string.te_quiero_tapas_bar_info,
                    address = R.string.te_quiero_tapas_bar_address,
                    contactNumber = R.string.te_quiero_tapas_bar_contact_number,
                    images = listOf(
                        R.drawable.te_quiero_tapas_bar_1,
                        R.drawable.te_quiero_tapas_bar_2
                    )
                ),
                Recommendation(
                    name = R.string.omai_khan,
                    information = R.string.omai_khan_info,
                    address = R.string.omai_khan_address,
                    contactNumber = R.string.omai_khan_contact_number,
                    images = listOf(
                        R.drawable.omai_khan_1,
                        R.drawable.omai_khan_2
                    )
                )
            )
        ),
        ShoppingCenter(
            recommendations = listOf(
                Recommendation(
                    name = R.string.sm_city_baguio,
                    information = R.string.sm_city_baguio_info,
                    address = R.string.sm_city_baguio_address,
                    images = listOf(
                        R.drawable.sm_city_baguio_1,
                        R.drawable.sm_city_baguio_2
                    )
                ),
                Recommendation(
                    name = R.string.porta_vaga_mall,
                    information = R.string.porta_vaga_mall_info,
                    address = R.string.porta_vaga_mall_address,
                    images = listOf(
                        R.drawable.porta_vaga_mall_1,
                        R.drawable.porta_vaga_mall_2
                    )
                ),
                Recommendation(
                    name = R.string.tiong_san_harrison,
                    information = R.string.tiong_san_harrison_info,
                    address = R.string.tiong_san_harrison_address,
                    images = listOf(
                        R.drawable.tiong_san_harrison_1,
                        R.drawable.tiong_san_harrison_2
                    )
                )
            )
        ),
        Park(
            recommendations = listOf(
                Recommendation(
                    name = R.string.burnham_park,
                    information = R.string.burnham_park_info,
                    address = R.string.burnham_park_address,
                    images = listOf(
                        R.drawable.burnham_park_1,
                        R.drawable.burnham_park_2
                    )
                ),
                Recommendation(
                    name = R.string.wright_park,
                    information = R.string.wright_park_info,
                    address = R.string.wright_park_address,
                    images = listOf(
                        R.drawable.wright_park_1,
                        R.drawable.wright_park_2
                    )
                ),
                Recommendation(
                    name = R.string.baguio_botanical_garden,
                    information = R.string.baguio_botanical_garden_info,
                    address = R.string.baguio_botanical_garden_address,
                    images = listOf(
                        R.drawable.botanical_garden_1,
                        R.drawable.botanical_garden_2,
                        R.drawable.botanical_garden_3
                    )
                ),
                Recommendation(
                    name = R.string.atonement_cathedral,
                    information = R.string.atonement_cathedral_info,
                    address = R.string.atonement_cathedral_address,
                    images = listOf(
                        R.drawable.our_lady_of_atonement_cathedral_1,
                        R.drawable.our_lady_of_atonement_cathedral_2
                    )
                ),
                Recommendation(
                    name = R.string.bell_church,
                    information = R.string.bell_church_info,
                    address = R.string.bell_church_address,
                    images = listOf(
                        R.drawable.bell_church_1,
                        R.drawable.bell_church_2
                    )
                )
            )
        )
    )

    val defaultCategory = allRecommendations[0]
    val defaultRecommendations = defaultCategory.recommendations
    val defaultRecommendation = defaultRecommendations[0]
}

