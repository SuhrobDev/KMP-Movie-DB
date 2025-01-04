//
//  HomeScreen.swift
//  iosApp
//
//  Created by suhrob on 27/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import SwiftUI

struct HomeScreen: View {
    @State private var path = NavigationPath()
    @ObservedObject private var viewModel: IOSHomeViewModel = IOSHomeViewModel()
    
    var body: some View {
        
        NavigationStack(path: $path) {
            // Horizontal ScrollView
            ScrollView(.horizontal, showsIndicators: false) {
                LazyHStack(spacing: 16) {
                    // Loop through the URLs and create an image view for each one
                    ForEach(viewModel.state.people, id: \.self) { url in
                        PeopleItemView(peopleItem: url, onItemClick: {model in
                            
                        })
                    }
                }
                .padding()
            }
        }.onAppear() {
            viewModel.startObserving()
            viewModel.onEvent(event: .GetPeople())
        }
    }
}
