package com.sajjadio.quickshop.data.dataSource.remote.mapper

import com.sajjadio.quickshop.data.dataSource.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.dataSource.remote.model.products.RatingDto
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.model.products.Rating

internal fun ProductDto.mapToProduct(): Product {
    return Product(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        rating = rating.toRatingDomain(),
        title = title
    )
}

private fun RatingDto.toRatingDomain(): Rating {
    return Rating(
        count = count,
        rate = rate
    )
}