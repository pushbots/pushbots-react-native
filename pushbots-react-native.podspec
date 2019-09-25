require 'json'
package_json = JSON.parse(File.read('package.json'))

Pod::Spec.new do |s|
  s.name           = "pushbots-react-native"
  s.version        = package_json["version"]
  s.homepage       = "https://github.com/pushbots/pushbots-react-native"
  s.summary        = package_json["description"]
  s.license        = package_json["license"]
  s.author         = { package_json["author"] => package_json["author"] }
  s.source         = { :git => "#{package_json["repository"]["url"]}.git", :tag => "#{s.version}" }
  s.source_files   = 'ios/RCTPushbots.{h,m}'
  s.static_framework = true  
  s.platform       = :ios, "9.0"
  s.dependency 'React',  '>= 0.13.0', '< 1.0.0'
  s.dependency 'react-native-netinfo', '4.2.2'
  s.dependency 'Pushbots', '2.4.3'
end