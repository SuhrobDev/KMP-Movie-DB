//
//  HomeScreen.swift
//  iosApp
//
//  Created by suhrob on 27/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI
import shared
import SwiftfulRouting

struct HomeScreen: View {
    
    @Environment(\.router) var router2
//    let router: AnyRouter
    
    @State private var path = NavigationPath()
    @ObservedObject private var viewModel: IOSHomeViewModel = IOSHomeViewModel()
    
    @State private var currentIndex: Int = 0
    private let timer = Timer.publish(every: 3, on: .main, in: .common).autoconnect()
    
    @State private var dragOffset: CGFloat = 0
    @State private var isDragging: Bool = false
    
    @State private var selectedMovie: MovieItemModel? = nil
    
    var body: some View {
            ScrollView(.vertical,showsIndicators: false) {
                VStack(alignment: .leading, spacing: 16) {
                    // Carousel Section
                    GeometryReader { geometry in
                        ZStack(alignment: .bottom) {
                            HStack(spacing: 0) {
                                ForEach(viewModel.state.nowPlayingMovies) { item in
                                    CarouselItemView(
                                        item: item,
                                        width: geometry.size.width,
                                        height: geometry.size.height) { movie in
                                            self.selectedMovie = movie
                                            
                                            router2.showScreen(.push, destination: {_ in 
                                                DetailScreen1(movieId: Int(movie.id))
                                            })
                                        }
                                }
                            }
                            .frame(
                                width: geometry.size.width * CGFloat(max(viewModel.state.nowPlayingMovies.count, 1)),
                                height: geometry.size.height
                            )
                            .offset(x: -CGFloat(currentIndex) * geometry.size.width)
                            .animation(.easeInOut(duration: 0.5), value: currentIndex)
                            .onReceive(timer) { _ in
                                guard !viewModel.state.nowPlayingMovies.isEmpty else { return }
                                withAnimation {
                                    currentIndex = (currentIndex + 1) % viewModel.state.nowPlayingMovies.count
                                }
                            }
                        }
                        //                        .navigationDestination(isPresented: Binding(
                        //                            get: { selectedMovie != nil },
                        //                            set: { if !$0 { selectedMovie = nil } }
                        //                        )) {//navigation trashmi hozir shundanmi muammo yoq data kelyapti static bersez get qilganizda keyin nmadir bolyaptimikna
                        //                            //get togri qilinganmi ozi onAppearda call qilish kkmi event da, id notogri olib otyapmanmikin
                        //                            if let movie = selectedMovie {
                        //                                DetailScreen1(movieId: Int(movie.id))
                        //                            }
                        //                        }
                        
                    }
                    .frame(height: UIScreen.main.bounds.height * 0.62)
                    
                    Spacer().frame(height: 16)
                    
                    Text("People").padding(.leading,16).font(.title3)
                    
                    ScrollView(.horizontal, showsIndicators: false) {
                        LazyHStack(spacing: 16) {
                            if viewModel.state.people.isEmpty {
                                Text("No people found")
                                    .foregroundColor(.gray)
                                    .padding()
                            } else {
                                ForEach(viewModel.state.people, id: \.self) { person in
                                    PeopleItemView(peopleItem: person, onItemClick: { model in
                                        // Handle item click
                                    })
                                }
                            }
                        }
                        .padding(.horizontal, 16)
                    }
                    
                    Text("Popular").padding(.leading,16).font(.title3)
                    
                    ScrollView(.horizontal, showsIndicators: false) {
                        LazyHStack(spacing: 16) {
                            if viewModel.state.popularMovies.isEmpty {
                                Text("No people found")
                                    .foregroundColor(.gray)
                                    .padding()
                            } else {
                                ForEach(viewModel.state.popularMovies, id: \.self) { movie in
                                    PopularMovieItemView(movie: movie)
                                }
                            }
                        }
                        .padding(.horizontal, 16)
                    }
                }
                
                Spacer().frame(height: 72)
            }
            .edgesIgnoringSafeArea(.top)
        
        .onAppear {
                viewModel.onEvent(event: .GetPeople())
                viewModel.onEvent(event: .GetNowPlayingMovies())
                viewModel.onEvent(event: .GetPopularMovies())
            }
    }
}
