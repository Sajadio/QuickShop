package com.sajjadio.quickshop.data.remote.mapper

import com.sajjadio.quickshop.data.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.remote.model.products.RatingDto
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.model.products.Rating

internal fun ProductDto.toRatingDomain(): Product {
    return Product(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        rating = ratingDto.toRatingDomain(),
        title = title
    )
}

internal fun RatingDto.toRatingDomain(): Rating {
    return Rating(
        count = count,
        rate = rate
    )
}