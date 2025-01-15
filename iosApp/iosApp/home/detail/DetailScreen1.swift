//
//  DetailScreen1.swift
//  iosApp
//
//  Created by suhrob on 27/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI
import shared

struct DetailScreen1: View {
    @ObservedObject private var viewModel: IOSMovieDetailViewModel = IOSMovieDetailViewModel()
    
    @Environment(\.router) private var router
    
    var movieId: Int
    
    var body: some View {
        
        ScrollView(.vertical) {
            VStack {
                ZStack(alignment: .center){
                    ZStack(alignment: .bottom) {
                        AsyncImage(url: URL(string: viewModel.state.movieDetail?.backdropUrl ?? "")) { phase in
                            switch phase {
                            case .empty:
                                ProgressView()
                            case .success(let image):
                                image
                                    .resizable()
                                    .scaledToFill()
                                    .clipped()
                            case .failure:
                                Image(systemName: "photo")
                                    .resizable()
                                    .scaledToFit()
                                    .foregroundColor(.gray)
                            @unknown default:
                                EmptyView()
                            }
                        }
                        .frame(height: UIScreen.main.bounds.height * 0.62)
                        
                        LinearGradient(
                            gradient: Gradient(colors: [.clear, .clear, .black]),
                            startPoint: .top,
                            endPoint: .bottom
                        )
                        // Text elements
                        HStack(alignment: .bottom, spacing: 4) {
                            Text(viewModel.state.movieDetail?.title ?? "")
                                .font(.title2)
                                .fontWeight(.bold)
                                .foregroundColor(.white)
                            
                            Spacer()
                            
                            IMDbText(rating: viewModel.state.movieDetail?.vote_average as? Double ?? 0.0)
                        }.padding(.bottom, 16).padding(.horizontal, 16)
                        
                    }
                    .frame(height: UIScreen.main.bounds.height * 0.62)
                    
                    // Play button
                    Button(action: {
                        print("Play button tapped")
                    }) {
                        Image(systemName: "play.fill")
                            .resizable()
                            .scaledToFit()
                            .foregroundColor(.white)
                            .frame(width: 24, height: 24)
                            .padding()
                            .background(Circle().fill(Color.blue)).opacity(0.8)
                    }.padding(.top, 16)
                    
                }
            }
        }
        .edgesIgnoringSafeArea(.top)
        .onAppear {
            viewModel.startObserving()
            viewModel.onEvent(event: .Detail(movieId: Int64(movieId)))
        }
    }
}
