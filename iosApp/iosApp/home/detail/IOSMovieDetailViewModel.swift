//
//  IOSMovieDetailViewModel.swift
//  iosApp
//
//  Created by suhrob on 06/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//
import Foundation
import shared

extension DetailScreen1{
    
    @MainActor class IOSMovieDetailViewModel: ObservableObject {
        
        private let service: HomeRepository
        private let vm: MovieDetailViewModel
        
        @Published var state: MovieDetailState = MovieDetailState(isLoading: false, error: "", movieDetail: nil,  actors: [], similarMovies: [])
                
        private var handle: DisposableHandle?
        
        init() {
            self.service = KoinDep().homeRepository
            self.vm = MovieDetailViewModel(repository: service, coroutineScope: nil)
            
//            startObserving()
        }
        
        func onEvent(event: MovieDetailIntent) {
            self.vm.handleIntent(intent: event)
        }
       
        func startObserving() {
        
            handle = vm.uiState.subscribe(onCollect: { state in
                guard let state = state else { return }
               
                DispatchQueue.main.async {
                    print("--------------------")
                    print(state)
                    self.state = state
                    print("statesss: \(state)")
                }
            })
        }
        
        deinit {
            handle?.dispose()
        }
        
    }
}
