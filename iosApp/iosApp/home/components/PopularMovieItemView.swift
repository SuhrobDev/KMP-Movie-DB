//
//  PopularMovieItemView.swift
//  iosApp
//
//  Created by suhrob on 05/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI
import shared

public struct PopularMovieItemView: View {
    @State var movie: MovieItemModel
    
    public var body: some View {
        VStack(alignment: .leading) {
                ZStack(alignment: .bottomTrailing) {
                    // Movie Image
                    AsyncImage(url: URL(string: movie.imageUrl)) { phase in
                        switch phase {
                        case .empty:
                            ProgressView()
                                .frame(width: 150, height: 200)
                        case .success(let image):
                            image
                                .resizable()
                                .scaledToFill()
                                .frame(width: 150, height: 200)
                                .cornerRadius(10)
                                .clipped()
                        case .failure:
                            Image(systemName: "photo")
                                .resizable()
                                .scaledToFit()
                                .foregroundColor(.gray)
                                .frame(width: 150, height: 200)
                                .cornerRadius(10)
                                .clipped()
                        @unknown default:
                            EmptyView()
                        }
                    }
                    
                    // Movie Type (e.g., +18 or +6) at the bottom right
                    Text(movie.adult as? Bool ?? false ? "+18" : "+6")
                        .font(.caption)
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                        .padding(8)
                        .background(Color.black.opacity(0.7))
                        .clipShape(Circle())
                        .padding(8)
                }
                
                // Movie Title
                Text(movie.title ?? "")
                    .font(.headline)
                    .foregroundColor(.white)
                    .multilineTextAlignment(.center)
                    .padding(.top, 8)
            }
            .frame(width: 150) // Set width for each item in the list
        }
}
