//
//  HomeViewModel.swift
//  iosApp
//
//  Created by suhrob on 27/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import Foundation
import shared

extension HomeScreen {
    @MainActor class IOSHomeViewModel: ObservableObject {
        
        private let service: HomeRepository
        private let vm :HomeViewModel
        
        @Published var state: HomeState = HomeState(isLoading: false, nowPlaying: nil, error: "", people: [], nowPlayingMovies: [], popularMovies: [])
                
        private var handle: DisposableHandle?
        
        init() {
            self.service = KoinDep().homeRepository
            self.vm = HomeViewModel(repository: service, coroutineScope: nil)
            
            startObserving()
        }
        
        func onEvent(event: HomeIntent) {
            self.vm.handleIntent(intent: event)
        }
       
        func startObserving() {
        
            handle = vm.uiState.subscribe(onCollect: { state in
                guard let state = state else { return }
               
                DispatchQueue.main.async {
                    self.state = state
                    print("stategg \(state)")
                }
            })
        }
        
        deinit {
            handle?.dispose()
        }
        
    }
    
}
