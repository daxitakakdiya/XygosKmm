import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
         ComposeView { _ in
MathScren()
                }
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



struct MathFormulaView: UIViewRepresentable {
    var formula: String

    func makeUIView(context: Context) -> MTMathUILabel {
        let label = MTMathUILabel()
        label.fontSize = 20
        let renderer = MathRendererFactory(label: label).createRenderer()
        renderer.render(formula: formula)
        return label
    }

    func updateUIView(_ uiView: MTMathUILabel, context: Context) {}
}




