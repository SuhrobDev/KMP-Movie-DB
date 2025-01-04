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
        
        @Published var data: String? = nil
        @Published var error: String? = nil
        @Published var loading: Bool = false
        
        @Published var state: HomeState = HomeState(isLoading: false, nowPlaying: nil, error: "", people: [])
                
        private var handle: DisposableHandle?
        
        init() {
            self.service = KoinDep().homeRepository
            self.vm = HomeViewModel(repository: service, coroutineScope: nil)
        }
        
        func onEvent(event: HomeIntent) {
            self.vm.handleIntent(intent: event)
        }
       
        func startObserving() {
        
            handle = vm.uiState.subscribe(onCollect: { state in
                guard let state = state else { return }
               
                DispatchQueue.main.async {
                    self.state = state
                }
            })
        }
        
        deinit {
            handle?.dispose()
        }
        
    }
    
}
