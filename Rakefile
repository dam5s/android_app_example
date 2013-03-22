task :default => [:test]

desc "Run Maven tests"
task :test do
  `mvn clean test`
end

namespace :robolectric do
  desc "Package Robolectric"
  task :package do
    print "Packaging Robolectric..."

    `cd submodules/robolectric`
    `mvn clean package`
    `cd ../..`

    puts " done."
  end

  desc "Install Robolectric"
  task :install do
    print "Installing Robolectric..."

    `mvn install:install-file \
      -Dfile=submodules/robolectric/target/robolectric-2.0-alpha-3-SNAPSHOT.jar \
      -DgroupId=org.robolectric \
      -DartifactId=robolectric \
      -Dversion=2.0-alpha-3-FORKED \
      -Dpackaging=jar`

    `cp pom.xml \
     ~/.m2/repository/org/robolectric/robolectric/2.0-alpha-3-FORKED/robolectric-2.0-alpha-3-FORKED.pom`

    puts " done."
  end

  desc "Package and Install Robolectric"
  task :update => [:package, :install]
end