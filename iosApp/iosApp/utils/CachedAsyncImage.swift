//
//  CachedAsyncImage.swift
//  iosApp
//
//  Created by suhrob on 05/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI

struct CachedAsyncImage: View {
    @State private var image: UIImage? = nil
    var url: URL
    
    var body: some View {
        Group {
            if let image = image {
                Image(uiImage: image)
                    .resizable()
                    .scaledToFill()
            } else {
                ProgressView()
                    .onAppear {
                        loadImage()
                    }
            }
        }
    }
    
    private func loadImage() {
        // Check cache first
        if let cachedImage = ImageCache().loadImage(from: url) {
            self.image = cachedImage
        } else {
            // If not cached, load image from URL
            URLSession.shared.dataTask(with: url) { data, response, error in
                if let data = data, let uiImage = UIImage(data: data) {
                    // Cache the image for later use
                    ImageCache().cacheImage(uiImage, for: url)
                    DispatchQueue.main.async {
                        self.image = uiImage
                    }
                }
            }.resume()
        }
    }
}
