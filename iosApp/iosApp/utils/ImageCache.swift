//
//  ImageCache.swift
//  iosApp
//
//  Created by suhrob on 05/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI

class ImageCache {
    private let cache = URLCache.shared
    
    func loadImage(from url: URL) -> UIImage? {
        // Try to retrieve from the cache
        if let cachedResponse = cache.cachedResponse(for: URLRequest(url: url)) {
            return UIImage(data: cachedResponse.data)
        }
        return nil
    }
    
    func cacheImage(_ image: UIImage, for url: URL) {
        let data = image.pngData() ?? image.jpegData(compressionQuality: 1.0)!
        let response = CachedURLResponse(
            response: URLResponse(url: url, mimeType: "image/png", expectedContentLength: data.count, textEncodingName: nil),
            data: data
        )
        cache.storeCachedResponse(response, for: URLRequest(url: url))
    }
}

